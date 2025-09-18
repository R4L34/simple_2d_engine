package com.r4l;


import com.r4l.simple_2d_engine.ecs.components.HitboxComponent;
import com.r4l.simple_2d_engine.gui.Screen;

@SuppressWarnings("serial")
public class TestScreen extends Screen{
	
	//private Button b;
	
	//private Text t;
	
	public TestScreen() {
		super();
	}
	/*
	 * Executes ones before the Screen is Created;
	 */
	@Override
	public void Init() {
		super.Init();

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
	}
	@Override
	public void onRelease(HitboxComponent hitbox) {
		super.onRelease(hitbox);
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
