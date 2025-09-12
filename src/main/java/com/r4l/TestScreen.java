package com.r4l;

import java.awt.Font;

import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.HitboxComponent;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.ecs.components.RenderComponent;
import com.r4l.simple_2d_engine.ecs.components.SizeComponent;
import com.r4l.simple_2d_engine.ecs.components.TextComponent;
import com.r4l.simple_2d_engine.gui.Screen;
import com.r4l.simple_2d_engine.util.HitboxType;

@SuppressWarnings("serial")
public class TestScreen extends Screen{
	
	private Entity e;
	
	public TestScreen() {
		super();
	}
	/*
	 * Executes ones before the Screen is Created;
	 */
	@Override
	public void Init() {
		super.Init();
		
		e = new Entity("Entity");
		//
		e.addComponent(new PositionComponent(10, 10));
		e.addComponent(new PositionComponent("TextPos", 20, 20));
		e.addComponent(new SizeComponent(100, 100));
		e.addComponent(new RenderComponent("/assets/ss.png", 1));
		e.addComponent(new TextComponent("Text", "TextPos", "Text", "Tahoma", Font.BOLD, 15, 3));
		e.addComponent(new HitboxComponent("HB1", HitboxType.HOVER));
		e.addComponent(new HitboxComponent("HB2", HitboxType.PRESS));
		e.addComponent(new HitboxComponent("HB3", HitboxType.RELEASE));
		ecs.addEntity(e);
	}
	
	/*
	 * Executes ones when the Screen is Opened;
	 */
	@Override
	public void onOpen() {
		super.onOpen();
	}
	
	/*
	 * Executes every frame, afrter onOpen();
	 */
	@Override
	public void Update() {
		super.Update();
	}
	
	
	@Override
	public void onPress(HitboxComponent hitbox) {
		super.onPress(hitbox);
		hitbox.getEntity().GetComponent(RenderComponent.class).setImage("/assets/ss1.png");
	}
	@Override
	public void onRelease(HitboxComponent hitbox) {
		super.onRelease(hitbox);
		hitbox.getEntity().GetComponent(RenderComponent.class).setImage("/assets/ss.png");
	}
	@Override
	public void onHover(HitboxComponent hitbox) {
		super.onHover(hitbox);
	}
	@Override
	public void onUnhover(HitboxComponent hitbox) {
		super.onUnhover(hitbox);
	}
}
