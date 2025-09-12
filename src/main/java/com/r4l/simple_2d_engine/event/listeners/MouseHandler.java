package com.r4l.simple_2d_engine.event.listeners;

import java.util.ArrayList;
import java.util.List;

import com.r4l.simple_2d_engine.Engine;
import com.r4l.simple_2d_engine.ecs.ECS;
import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.HitboxComponent;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.ecs.components.SizeComponent;
import com.r4l.simple_2d_engine.event.EventHandler;
import com.r4l.simple_2d_engine.event.SubscribeEvent;
import com.r4l.simple_2d_engine.event.events.ActionPerformedEvent;
import com.r4l.simple_2d_engine.event.events.EngineMouseEvent;
import com.r4l.simple_2d_engine.gui.Screen;
import com.r4l.simple_2d_engine.util.HitboxType;

@EventHandler
public class MouseHandler {
	
	private HitboxComponent isInRange(EngineMouseEvent.Base event, HitboxType hitboxType) {
	    ECS ecs = event.getScreen().getEcs();
	    List<HitboxComponent> candidates = new ArrayList<>();

	    for (Entity entity : ecs.getEntityList()) {
	        if (!entity.hasComponent(HitboxComponent.class)) continue;

	        for (HitboxComponent hitbox : entity.GetComponents(HitboxComponent.class)) {
	            if (hitbox.getHitboxType() == hitboxType && hitbox.isEnabled()) {
	                candidates.add(hitbox);
	            }
	        }
	    }

	    int mouseX = event.getX();
	    int mouseY = event.getY();

	    for (HitboxComponent hitbox : candidates) {
	        PositionComponent pos = hitbox.getPos();
	        SizeComponent size = hitbox.getSize();

	        if (mouseX >= pos.getX() && mouseX <= pos.getX() + size.getWidth()
	         && mouseY >= pos.getY() && mouseY <= pos.getY() + size.getHeight()) {
	            return hitbox;
	        }
	    }

	    return null;
	}

		
		
	
	//SubscribeEvents
	
		@SubscribeEvent
		public void onMousePressed(EngineMouseEvent.Pressed event) {
			HitboxComponent hitbox = isInRange(event, HitboxType.PRESS);
			if (hitbox == null) {return;}
			//Cast Events
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Pressed(event, event.getScreen(), hitbox));
			event.getScreen().onPress(hitbox);
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Pressed(event, event.getScreen(), hitbox));

		}
		
		@SubscribeEvent
		public void onMouseReleased(EngineMouseEvent.Released event) {
			HitboxComponent hitbox = isInRange(event, HitboxType.RELEASE);
			if (hitbox == null) {return;}
			//Cast Events
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Released(event, event.getScreen(), hitbox));
			event.getScreen().onRelease(hitbox);
			Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Released(event, event.getScreen(), hitbox));
		}
		
		@SubscribeEvent
		public void onMouseMoved(EngineMouseEvent.Moved event) {
		    Screen screen = event.getScreen();
		    ECS ecs = screen.getEcs();

		    // --- Hover Logic ---
		    HitboxComponent hovered = isInRange(event, HitboxType.HOVER);
		    if (hovered != null && !hovered.isHovered()) {
		        Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Hovered(event, screen, hovered));
		        screen.onHover(hovered);
		        Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Hovered(event, screen, hovered));
		        hovered.setHovered(true);
		        return;
		    }

		    // --- Unhover Logic ---
		    int mouseX = event.getX();
		    int mouseY = event.getY();

		    // Collect all candidate hitboxes without mutating ECS
		    List<HitboxComponent> candidates = new ArrayList<>();
		    for (Entity entity : ecs.getEntityList()) {
		        if (!entity.hasComponent(HitboxComponent.class)) continue;

		        for (HitboxComponent hb : entity.GetComponents(HitboxComponent.class)) {
		            if (hb.getHitboxType() == HitboxType.HOVER && hb.isEnabled()) {
		                candidates.add(hb);
		            }
		        }
		    }

		    // Check which ones should be unhovered
		    for (HitboxComponent hb : candidates) {
		        PositionComponent pos = hb.getPos();
		        SizeComponent size = hb.getSize();

		        boolean insideX = mouseX >= pos.getX() && mouseX <= pos.getX() + size.getWidth();
		        boolean insideY = mouseY >= pos.getY() && mouseY <= pos.getY() + size.getHeight();

		        if (!(insideX && insideY) && hb.isHovered()) {
		            Engine.EVENT_BUS.post(new ActionPerformedEvent.Pre.Unhovered(event, screen, hb));
		            screen.onUnhover(hb);
		            Engine.EVENT_BUS.post(new ActionPerformedEvent.Post.Unhovered(event, screen, hb));
		            hb.setHovered(false); 
		        }
		    }
		}

}
