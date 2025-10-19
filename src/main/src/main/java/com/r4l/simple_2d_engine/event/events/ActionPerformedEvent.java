package com.r4l.simple_2d_engine.event.events;

import java.awt.Component;
import java.awt.event.MouseEvent;

import com.r4l.simple_2d_engine.ecs.components.HitboxComponent;
import com.r4l.simple_2d_engine.gui.Screen;


public class ActionPerformedEvent {
	
	// Base abstract class for all custom mouse events
    @SuppressWarnings("serial")
	public static abstract class Base extends MouseEvent {
    	
        private final Screen screen;
        
        private final HitboxComponent hitbox;

        public Base(MouseEvent e, Screen screen, HitboxComponent hitbox) {
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
            this.hitbox = hitbox;
        }

        public Screen getScreen() {
            return screen;
        }

		public HitboxComponent getHitbox() {
			return hitbox;
		}
    }
    
    
    public static class Pre{
    
    @SuppressWarnings("serial")
	public static class Pressed extends Base {
        public Pressed(MouseEvent e, Screen screen, HitboxComponent hitbox) {
            super(e, screen, hitbox);
        }
    }

    @SuppressWarnings("serial")
	public static class Released extends Base {
        public Released(MouseEvent e, Screen screen, HitboxComponent hitbox) {
        	super(e, screen, hitbox);
        }
    }


    @SuppressWarnings("serial")
	public static class Hovered extends Base {
        public Hovered(MouseEvent e, Screen screen, HitboxComponent hitbox) {
        	super(e, screen, hitbox);
        }
    }
    
    @SuppressWarnings("serial")
   	public static class Unhovered extends Base {
           public Unhovered(MouseEvent e, Screen screen, HitboxComponent hitbox) {
        	   super(e, screen, hitbox);
           }
       }
    
    }
    
    
    
    public static class Post{
        
        @SuppressWarnings("serial")
    	public static class Pressed extends Base {
            public Pressed(MouseEvent e, Screen screen, HitboxComponent hitbox) {
                super(e, screen, hitbox);
            }
        }

        @SuppressWarnings("serial")
    	public static class Released extends Base {
            public Released(MouseEvent e, Screen screen, HitboxComponent hitbox) {
                super(e, screen, hitbox);
            }
        }


        @SuppressWarnings("serial")
    	public static class Hovered extends Base {
            public Hovered(MouseEvent e, Screen screen, HitboxComponent hitbox) {
                super(e, screen, hitbox);
            }
        }
        
        @SuppressWarnings("serial")
       	public static class Unhovered extends Base {
               public Unhovered(MouseEvent e, Screen screen, HitboxComponent hitbox) {
                   super(e, screen, hitbox);
               }
           }
        
        }

}
