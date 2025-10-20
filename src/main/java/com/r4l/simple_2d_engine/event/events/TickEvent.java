package com.r4l.simple_2d_engine.event.events;

import com.r4l.simple_2d_engine.gui.Screen;

public class TickEvent {
	
	private Screen screen;
	
	public TickEvent(Screen screen) {
		this.screen = screen;
	}

	public Screen getScreen() {
		return screen;
	}
	
	public static class Pre extends TickEvent {

		public Pre(Screen screen) {
			super(screen);
		}
		
	}

}
