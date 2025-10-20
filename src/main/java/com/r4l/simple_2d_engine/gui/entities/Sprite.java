package com.r4l.simple_2d_engine.gui.entities;

import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.ecs.components.RenderComponent;
import com.r4l.simple_2d_engine.ecs.components.SizeComponent;

public class Sprite extends Entity{

	public Sprite(String name,  int x, int y, int width, int height, String resourceLocation, int zOrder) {
		super(name, "Sprite");
		
		addComponent(new PositionComponent(x, y));
		addComponent(new SizeComponent(width, height));
		addComponent(new RenderComponent(resourceLocation, zOrder));
	}
	
	public Sprite(String name,  double x, double y, double width, double height, String resourceLocation, int zOrder) {
		super(name, "Sprite");
		
		addComponent(new PositionComponent(x, y));
		addComponent(new SizeComponent(width, height));
		addComponent(new RenderComponent(resourceLocation, zOrder));
	}

}
