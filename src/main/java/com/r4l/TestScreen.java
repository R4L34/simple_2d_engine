package com.r4l;

import java.awt.Font;
import java.awt.Graphics;

import com.r4l.r4ls_2d_engine.ecs.Entity;
import com.r4l.r4ls_2d_engine.ecs.components.PositionComponent;
import com.r4l.r4ls_2d_engine.ecs.components.RenderComponent;
import com.r4l.r4ls_2d_engine.ecs.components.SizeComponent;
import com.r4l.r4ls_2d_engine.ecs.components.TextComponent;
import com.r4l.r4ls_2d_engine.gui.Screen;

@SuppressWarnings("serial")
public class TestScreen extends Screen{
	
	private Entity e;
	
	private Entity e2;
	
	public TestScreen() {
		super();
	}
	
	/*
	 * Executes ones before the Screen is Created;
	 */
	public void Init() {
		super.Init();
		
		e = new Entity("Ass", "Renderable");
		e.addComponent(new PositionComponent(120, 120));
		e.addComponent(new TextComponent("Text", "Tahoma", Font.BOLD, 35, 1));
		ecs.addEntity(e);
		
		e2 = new Entity("Ass1", "Renderable");
		e2.addComponent(new PositionComponent(100, 100));
		e2.addComponent(new SizeComponent(100, 100));
		e2.addComponent(new RenderComponent("/assets/ss1.png", 0));
		ecs.addEntity(e2);
	}
	
	/*
	 * Executes ones when the Screen is Created;
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
	
	/*
	 * Executes before a Screen is closed;
	 */
	public void onClose() {
		super.onClose();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	@Override
	public void onClick(Entity e) {
		super.onClick(e);
	}
	
	@Override
	public void onPress(Entity e) {
		super.onPress(e);
		if (e == this.e) {
			e.GetComponent(RenderComponent.class).setImage("/assets/ss1.png");
		}
	}
	
	@Override
	public void onRelease(Entity e) {
		super.onRelease(e);
	}
	
	@Override
	public void onHover(Entity e) {
		super.onHover(e);
		if (e == this.e) {
			e.GetComponent(RenderComponent.class).setImage("/assets/ss1.png");
		}
	}
	
	@Override
	public void onUnHover(Entity e) {
		super.onUnHover(e);
	}
}
