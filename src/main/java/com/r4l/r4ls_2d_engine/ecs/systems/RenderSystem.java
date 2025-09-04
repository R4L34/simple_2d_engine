package com.r4l.r4ls_2d_engine.ecs.systems;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Comparator;
import java.util.List;

import com.r4l.r4ls_2d_engine.ecs.ECS;
import com.r4l.r4ls_2d_engine.ecs.Entity;
import com.r4l.r4ls_2d_engine.ecs.ISystem;
import com.r4l.r4ls_2d_engine.ecs.components.PositionComponent;
import com.r4l.r4ls_2d_engine.ecs.components.RenderComponent;
import com.r4l.r4ls_2d_engine.ecs.components.SizeComponent;
import com.r4l.r4ls_2d_engine.ecs.components.TextComponent;

public class RenderSystem extends ISystem{
	
	private ECS ecs;
	
	public RenderSystem(ECS ecs) {
		setEcs(ecs);
		addDependency(PositionComponent.class);
		addDependency(SizeComponent.class);
		addDependency(RenderComponent.class);
	}
	
	public void render (Entity e, Graphics2D g2) {
		if(ecs != e.getECS()) {
			System.out.println("Entity " + e.getName() + " is not a part of provided ECS");
			return;
		}
		
		if (e.hasDependencies(dependencies)) {
			PositionComponent pos = e.GetComponent(PositionComponent.class);
			SizeComponent size = e.GetComponent(SizeComponent.class);
			RenderComponent rc = e.GetComponent(RenderComponent.class);
			
			g2.drawImage(rc.getImage(), pos.getX(), pos.getY(), size.getWidth(), size.getHeight(), null);
			
		}
			
		if (e.hasDependencies(new TextSystem().getDependencies())) {
			PositionComponent pos = e.GetComponent(PositionComponent.class);
			TextComponent text = e.GetComponent(TextComponent.class);
			
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setFont(text.getFont());
			g2.drawString(text.getText(), pos.getX(), pos.getY());

		}
		
	}
	
	public void renderAllRenderable(Graphics2D g2) {
		List<Entity> entities = ecs.GetEntitiesByGroup("Renderable", Entity.class);
		
		// Sorting
		entities.sort(Comparator.comparingInt(e -> {
		    if (e.hasComponent(RenderComponent.class)) {
		        return e.GetComponent(RenderComponent.class).getZOrder();
		    } else if (e.hasComponent(TextComponent.class)) {
		        return e.GetComponent(TextComponent.class).getZOrder();
		    } else {
		        // fallback if neither component exists
		        return 1; 
		    }
		}));
		//Rendering
		for(Entity e : entities) {
			render(e, g2);
		}
		
	}

	public ECS getEcs() {
		return ecs;
	}

	public void setEcs(ECS ecs) {
		this.ecs = ecs;
	}
	
	
	public static class TextSystem extends ISystem{	
		public TextSystem() {
			addDependency(PositionComponent.class);
			addDependency(TextComponent.class);
		}
	}

}
