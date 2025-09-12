package com.r4l.simple_2d_engine.event.events;

import java.awt.Component;
import java.awt.event.MouseEvent;

import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.gui.Screen;


public class ActionPerformedEvent {
	
	// Base abstract class for all custom mouse events
    @SuppressWarnings("serial")
	public static abstract class Base extends MouseEvent {
    	
        private final Screen screen;
        
        private final Entity entity;

        public Base(MouseEvent e, Screen screen, Entity entity) {
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
            this.entity = entity;
        }

        public Screen getScreen() {
            return screen;
        }

		public Entity getEntity() {
			return entity;
		}
    }
    
    
    public static class Pre{
    
    @SuppressWarnings("serial")
	public static class Pressed extends Base {
        public Pressed(MouseEvent e, Screen screen, Entity entity) {
            super(e, screen, entity);
        }
    }

    @SuppressWarnings("serial")
	public static class Released extends Base {
        public Released(MouseEvent e, Screen screen, Entity entity) {
            super(e, screen, entity);
        }
    }


    @SuppressWarnings("serial")
	public static class Hovered extends Base {
        public Hovered(MouseEvent e, Screen screen, Entity entity) {
            super(e, screen, entity);
        }
    }
    
    @SuppressWarnings("serial")
   	public static class Unhovered extends Base {
           public Unhovered(MouseEvent e, Screen screen, Entity entity) {
               super(e, screen, entity);
           }
       }
    
    }
    
    
    
    public static class Post{
        
        @SuppressWarnings("serial")
    	public static class Pressed extends Base {
            public Pressed(MouseEvent e, Screen screen, Entity entity) {
                super(e, screen, entity);
            }
        }

        @SuppressWarnings("serial")
    	public static class Released extends Base {
            public Released(MouseEvent e, Screen screen, Entity entity) {
                super(e, screen, entity);
            }
        }


        @SuppressWarnings("serial")
    	public static class Hovered extends Base {
            public Hovered(MouseEvent e, Screen screen, Entity entity) {
                super(e, screen, entity);
            }
        }
        
        @SuppressWarnings("serial")
       	public static class Unhovered extends Base {
               public Unhovered(MouseEvent e, Screen screen, Entity entity) {
                   super(e, screen, entity);
               }
           }
        
        }

}
