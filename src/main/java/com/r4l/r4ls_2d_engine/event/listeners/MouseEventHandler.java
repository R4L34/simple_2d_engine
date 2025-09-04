package com.r4l.r4ls_2d_engine.event.listeners;

import java.util.Iterator;
import java.util.List;

import com.r4l.r4ls_2d_engine.Engine;
import com.r4l.r4ls_2d_engine.ecs.ECS;
import com.r4l.r4ls_2d_engine.ecs.Entity;
import com.r4l.r4ls_2d_engine.ecs.components.HitboxComponent;
import com.r4l.r4ls_2d_engine.ecs.components.PositionComponent;
import com.r4l.r4ls_2d_engine.event.EventHandler;
import com.r4l.r4ls_2d_engine.event.SubscribeEvent;
import com.r4l.r4ls_2d_engine.event.events.ActionPerformedEvent;
import com.r4l.r4ls_2d_engine.event.events.EngineMouseEvent;
import com.r4l.r4ls_2d_engine.gui.Screen;
import com.r4l.r4ls_2d_engine.util.HitboxType;

@EventHandler
public class MouseEventHandler {
	
	private boolean isInRange(EngineMouseEvent.Base event, Entity e, HitboxType hitboxType) {
		int mouseX = event.getX();
		int mouseY = event.getY();
		
		if(!(e.hasComponent(HitboxComponent.class) && e.hasComponent(PositionComponent.class))) { return false;}
		
		PositionComponent pos = e.GetComponent(PositionComponent.class);
		List<HitboxComponent> hitboxes = e.GetComponents(HitboxComponent.class);
		
		
		//Safe Removal
		Iterator<HitboxComponent> it = hitboxes.iterator();
		while (it.hasNext()) {
		    HitboxComponent hitbox = it.next();
		    if (hitbox.getHitboxType() != hitboxType) {
		        it.remove();  // safe removal
		    }
		}

		
		if (hitboxes.size() == 0) {return false;}
		
		//hitbox implementation
		
		for(HitboxComponent hitbox : hitboxes) {
			boolean isWithinWidth = false;
			boolean isWithinHeight = false;
			
			if(mouseX >= pos.getX() && mouseX <= pos.getX() + hitbox.getWidth()) {
				isWithinWidth = true;
			}
			
			if(mouseY >= pos.getY() && mouseY <= pos.getY() + hitbox.getHeight()) {
				isWithinHeight = true;
			}
			
			if(isWithinWidth && isWithinHeight) {
				return true;
			}
		}
		
		return false;
	}
	
	private static interface ModifiedRunnable {
		public void run(Entity entity);
	}

	

	private void triggerEvent(EngineMouseEvent.Base event, HitboxType hitboxType,  ModifiedRunnable task) {
		Screen screen = event.getScreen();
		ECS ecs = screen.getEcs();
		List<Entity> entities = ecs.getEntityList();
		
		for(Entity entity : entities) {
			if(isInRange(event, entity, hitboxType)) {
				task.run(entity);
			}
		}
	}
	
	//SubscribeEvents
	
	@SubscribeEvent
	public void onMousePressed(EngineMouseEvent.Pressed event) {
		triggerEvent(event, HitboxType.PRESS, (entity) -> {
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Pressed(event, event.getScreen(), entity));
			event.getScreen().onPress(entity);
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Pressed(event, event.getScreen(), entity));
		});
	}
	
	@SubscribeEvent
	public void onMouseClicked(EngineMouseEvent.Clicked event) {
		triggerEvent(event, HitboxType.CLICK, (entity) -> {
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Clicked(event, event.getScreen(), entity));
			event.getScreen().onClick(entity);
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Clicked(event, event.getScreen(), entity));
		});
	}
	
	@SubscribeEvent
	public void onMouseReleased(EngineMouseEvent.Released event) {
		triggerEvent(event, HitboxType.RELEASE, (entity) -> {
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Released(event, event.getScreen(), entity));
			event.getScreen().onRelease(entity);
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Released(event, event.getScreen(), entity));
		});
	}
	
	@SubscribeEvent
	public void onMouseMoved(EngineMouseEvent.Moved event) {
		HitboxType hitboxType = HitboxType.HOVER;
		
		Screen screen = event.getScreen();
		ECS ecs = screen.getEcs();
		List<Entity> entities = ecs.getEntityList();
		
		for(Entity entity : entities) {
			if(isInRange(event, entity, hitboxType)) {
				if(!entity.isHovered()) {
					Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Hovered(event, event.getScreen(), entity));
					screen.onHover(entity);
					Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Hovered(event, event.getScreen(), entity));
					entity.setHovered(true);
				}
			}else {
				if(entity.isHovered()) {
					Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Unhovered(event, event.getScreen(), entity));
					screen.onUnHover(entity);
					Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Unhovered(event, event.getScreen(), entity));
					entity.setHovered(false);
				}
			}
		}
	}
	
	
}
