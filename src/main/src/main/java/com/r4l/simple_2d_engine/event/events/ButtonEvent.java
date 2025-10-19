package com.r4l.simple_2d_engine.event.events;


import com.r4l.simple_2d_engine.gui.Screen;
import com.r4l.simple_2d_engine.gui.entities.Button;

public class ButtonEvent {
	
	private Screen screen;
	
	private Button button;
	
	public ButtonEvent (Screen screen, Button button) {
		this.screen = screen;
		this.button = button;
	}
	
	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
	
	

	public static class Click extends ButtonEvent{

		public Click(Screen screen, Button button) {
			super(screen, button);
		}
		
	}
	
	public static class Press extends ButtonEvent{

		public Press(Screen screen, Button button) {
			super(screen, button);
		}
		
	}
	
	public static class Release extends ButtonEvent{

		public Release(Screen screen, Button button) {
			super(screen, button);
		}
		
	}
	
	public static class Hover extends ButtonEvent{

		public Hover(Screen screen, Button button) {
			super(screen, button);
		}
		
	}
	
	public static class Unhover extends ButtonEvent{

		public Unhover(Screen screen, Button button) {
			super(screen, button);
		}
		
	}

}
