package com.r4l.simple_2d_engine.event.listeners;

import java.util.HashSet;
import java.util.Set;

import com.r4l.simple_2d_engine.Engine;
import com.r4l.simple_2d_engine.event.EventHandler;
import com.r4l.simple_2d_engine.event.SubscribeEvent;
import com.r4l.simple_2d_engine.event.events.EngineKeyEvent;

@EventHandler
public class KeyHandler {
	
	private Set<Character> pressed = new HashSet<>();
	
	@SubscribeEvent
	public void onKeyPressed(EngineKeyEvent.Pressed e) {
		if(pressed.contains(e.getKeyChar())) {return;}
		pressed.add(e.getKeyChar());
		Engine.EVENT_BUS.post(new EngineKeyEvent.PressedOnes(e.getScreen(), e.getKeyChar(), e.getKeyCode()));
	}
	
	
	@SubscribeEvent
	public void onKeyReleased(EngineKeyEvent.Released e) {
		if(pressed.contains(e.getKeyChar())) {
			pressed.remove(e.getKeyChar());
		}
	}

}
