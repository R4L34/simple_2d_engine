package com.r4l.simple_2d_engine.event.events;

import com.r4l.simple_2d_engine.gui.Screen;

public class EngineKeyEvent {
	
	public static class Pressed {
		
		private Screen screen;
		
		private char keyChar;
		
		private int keyCode;
		
		public Pressed(Screen screen, char keyChar, int keyCode) {
			this.screen = screen;
			this.keyChar = keyChar;
			this.keyCode = keyCode;
		}

		public Screen getScreen() {
			return screen;
		}

		public char getKeyChar() {
			return keyChar;
		}

		public int getKeyCode() {
			return keyCode;
		}
		
	}
	
	
public static class PressedOnes {
		
		private Screen screen;
		
		private char keyChar;
		
		private int keyCode;
		
		public PressedOnes(Screen screen, char keyChar, int keyCode) {
			this.screen = screen;
			this.keyChar = keyChar;
			this.keyCode = keyCode;
		}

		public Screen getScreen() {
			return screen;
		}

		public char getKeyChar() {
			return keyChar;
		}

		public int getKeyCode() {
			return keyCode;
		}
		
	}
	
	
public static class Released {
		
		private Screen screen;
		
		private char keyChar;
		
		private int keyCode;
		
		public Released(Screen screen, char keyChar, int keyCode) {
			this.screen = screen;
			this.keyChar = keyChar;
			this.keyCode = keyCode;
		}

		public Screen getScreen() {
			return screen;
		}

		public char getKeyChar() {
			return keyChar;
		}

		public int getKeyCode() {
			return keyCode;
		}
		
	}
}
