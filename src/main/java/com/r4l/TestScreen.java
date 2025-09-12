package com.r4l;

import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.ecs.components.RenderComponent;
import com.r4l.simple_2d_engine.ecs.components.SizeComponent;
import com.r4l.simple_2d_engine.gui.Screen;

@SuppressWarnings("serial")
public class TestScreen extends Screen{
	
	private Entity e;
	
	public TestScreen() {
		super();
	}
	/*
	 * Executes ones before the Screen is Created;
	 */
	public void Init() {
		super.Init();
		
		e = new Entity("Entity");
		ecs.addEntity(e);
		//
		e.addComponent(new PositionComponent(10, 10));
		e.addComponent(new SizeComponent(100, 100));
		e.addComponent(new RenderComponent("/assets/ss.png", 1));
	}
	
	/*
	 * Executes ones when the Screen is Opened;
	 */
	public void onOpen() {
		super.onOpen();
	}
	
	/*
	 * Executes every frame, afrter onOpen();
	 */
	public void Update() {
		super.Update();
	}
}
