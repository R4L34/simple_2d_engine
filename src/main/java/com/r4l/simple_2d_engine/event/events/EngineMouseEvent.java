package com.r4l.simple_2d_engine.event.events;

import java.awt.Component;
import java.awt.event.MouseEvent;

import com.r4l.simple_2d_engine.gui.Screen;

public class EngineMouseEvent {

    // Base abstract class for all custom mouse events
    @SuppressWarnings("serial")
	public static abstract class Base extends MouseEvent {
        private final Screen screen;

        public Base(MouseEvent e, Screen screen) {
            super((Component) e.getSource(),
                  e.getID(),
                  e.getWhen(),
                  e.getModifiersEx(),
                  e.getX(),
                  e.getY(),
                  e.getClickCount(),
                  e.isPopupTrigger(),
                  e.getButton());
            this.screen = screen;
        }

        public Screen getScreen() {
            return screen;
        }
    }


    
    @SuppressWarnings("serial")
	public static class Pressed extends Base {
        public Pressed(MouseEvent e, Screen screen) {
            super(e, screen);
        }
    }

    @SuppressWarnings("serial")
	public static class Released extends Base {
        public Released(MouseEvent e, Screen screen) {
            super(e, screen);
        }
    }

    @SuppressWarnings("serial")
	public static class Moved extends Base {
        public Moved(MouseEvent e, Screen screen) {
            super(e, screen);
        }
    }

    @SuppressWarnings("serial")
	public static class Dragged extends Base {
        public Dragged(MouseEvent e, Screen screen) {
            super(e, screen);
        }
    }
}
