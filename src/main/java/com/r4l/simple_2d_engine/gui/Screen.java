package com.r4l.simple_2d_engine.gui;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.r4l.simple_2d_engine.util.Renderable;
import com.r4l.simple_2d_engine.Engine;
import com.r4l.simple_2d_engine.ecs.ECS;
import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.HitboxComponent;
import com.r4l.simple_2d_engine.ecs.components.RenderComponent;
import com.r4l.simple_2d_engine.ecs.components.TextComponent;
import com.r4l.simple_2d_engine.event.events.EngineKeyEvent;
import com.r4l.simple_2d_engine.event.events.EngineMouseEvent;
import com.r4l.simple_2d_engine.event.events.TickEvent;
import com.r4l.simple_2d_engine.gui.entities.Button;
import com.r4l.simple_2d_engine.util.Loop;
import com.r4l.simple_2d_engine.util.Reference;

@SuppressWarnings("serial")
public class Screen extends Canvas{
	
	protected ECS ecs;
	
	private Loop gameLoop;
	
	protected BufferStrategy bufferStrategy;
	
	private MouseData mouseData;
	
	private KeyData keyData;
	
	private int updateCount; //Only used to execute onPostUpdate once

	public Screen() {
		ecs = new ECS(this);
		mouseData = new MouseData();
		keyData = new KeyData();
		addMouseListeners();
		addKeyListeners();
		Init();
	}
	
	/*
	 * Executes ones before the Screen is Created;
	 */
	public void Init() {
		
	}
	
	/*
	 * Executes ones when the Screen is Opened but before Update;
	 */
	public void onOpen() {
		
	}
	
	/*
	 * Executes every frame, afrter onOpen();
	 */
	public void Update() {
		
	}
	
	/*
	 * Executes ones when the Screen is Opened after first tick of Update;
	 */
	public void onOpenPost() {
		
	}
	
	/*
	 * Executes every frame, afrter Update();
	 */
	public void renderUpdate(Graphics2D g2) {
		//White Background
		g2.setColor(Reference.MAIN_SCREEN_BG_COLOR);
		g2.fillRect(0, 0, Reference.FINAL_RESOLUTION.width, Reference.FINAL_RESOLUTION.height);
		//Rendering
		// Collect all render components
	    List<Renderable> components = new ArrayList<>();
	    
	    for (Entity e : ecs.getEntityList()) {
	    	components.addAll(e.GetComponents(RenderComponent.class));
	    	components.addAll(e.GetComponents(TextComponent.class));
	    }
		
	    // Sort by zOrder
	    components.sort(Comparator.comparingInt(Renderable::getZOrder));
		
	    // Render in sorted order
	    for (Renderable c : components) {
	    	if (c instanceof RenderComponent) {
	    		RenderComponent r = (RenderComponent) c;
	    		r.render(g2);
	    	}else if(c instanceof TextComponent) {
	    		TextComponent r = (TextComponent) c;
	    		r.render(g2);
	    	}
	    }
	}
	
	/*
	 * Executes before a Screen is closed;
	 */
	public void onClose() {
		gameLoop.destroyLoop();
	}
	

	
	public void onPress(HitboxComponent hitbox) {
		Entity e = hitbox.getEntity();
		if(hitbox.getEntity().getGroups().contains("Button")) {
			((Button) e).onPress();
		}
		
	}
	
	public void onRelease(HitboxComponent hitbox) {
		Entity e = hitbox.getEntity();
		if(hitbox.getEntity().getGroups().contains("Button")) {
			((Button) e).onRelease();
		}
	}
	
	public void onHover(HitboxComponent hitbox) {
		Entity e = hitbox.getEntity();
		if(hitbox.getEntity().getGroups().contains("Button")) {
			((Button) e).onHover();
		}
	}
	
	public void onUnhover(HitboxComponent hitbox) {
		Entity e = hitbox.getEntity();
		if(hitbox.getEntity().getGroups().contains("Button")) {
			((Button) e).onUnhover();
		}
	}
	
	
	
	/////////////////// Technical //////////////////////
	
	
	public void startUpdate() {
		gameLoop = new Loop("ScreenLoop", () -> {
			
			Engine.EVENT_BUS.post(new TickEvent.Pre(this));

	        if (bufferStrategy == null) {
	            createBufferStrategy(3);
	            bufferStrategy = getBufferStrategy();
	        }
	        
	        Graphics2D g2 = (Graphics2D) bufferStrategy.getDrawGraphics();
			
			eventCaller();
			
			Update();

			renderUpdate(g2);
			
			Engine.EVENT_BUS.post(new TickEvent(this));
			
			//Dispose and show Graphics
			g2.dispose();
			try {
				bufferStrategy.show();
			} catch (java.lang.NullPointerException e) {
				System.out.println("NPE Occured while executing bufferStrategy.show()");
			}
			Toolkit.getDefaultToolkit().sync();
			
			if (updateCount == 0) {
				onOpenPost();
				updateCount++;
			}
	        
		});
	}
	
	/////////////////// Getters && Setters //////////////////////

	
	
	public ECS getEcs() {
		return ecs;
	}

	public Loop getGameLoop() {
		return gameLoop;
	}
	
	
	/////////////////// Technical //////////////////////
	private void addMouseListeners() {
		addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
            	mouseData.setMousePressedEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	mouseData.setMouseReleasedEvent(e);
            }
        });

        // Mouse motion (dragging & moving)
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
            	mouseData.setMouseMovedEvent(e);
            }

            //Not Implemented Yet
            @Override
            public void mouseDragged(MouseEvent e) {
            	mouseData.setMouseDraggedEvent(e);
            }
        });
	}
	
	private void addKeyListeners() {
	    addKeyListener(new KeyListener() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            keyData.addKeyPressed(e);
	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	            keyData.addKeyReleased(e);
	        }

			@Override
			public void keyTyped(KeyEvent e) {
				// Unused
			}
	    });
	}
	
	private void eventCaller() {
		if (mouseData.getMousePressedEvent() != null) {
			Engine.EVENT_BUS.post(new EngineMouseEvent.Pressed(mouseData.getMousePressedEvent(), this));
		}
		
		if (mouseData.getMouseReleasedEvent() != null) {
			Engine.EVENT_BUS.post(new EngineMouseEvent.Released(mouseData.getMouseReleasedEvent(), this));
		}
		
		if (mouseData.getMouseMovedEvent() != null) {
			Engine.EVENT_BUS.post(new EngineMouseEvent.Moved(mouseData.getMouseMovedEvent(), this));
		}
		
		if (mouseData.getMouseDraggedEvent() != null) {
			Engine.EVENT_BUS.post(new EngineMouseEvent.Dragged(mouseData.getMouseDraggedEvent(), this));
		}
		
		for (KeyEvent pressed : keyData.getCurrentlyPressed()) {
	        Engine.EVENT_BUS.post(new EngineKeyEvent.Pressed(this, pressed.getKeyChar(), pressed.getKeyCode()));
	    }
		
		for (KeyEvent released : keyData.getCurrentlyReleased()) {
	        Engine.EVENT_BUS.post(new EngineKeyEvent.Released(this, released.getKeyChar(), released.getKeyCode()));
	    }
		
		mouseData.flush();
		keyData.flush();
		
	}
	
	
	private static class MouseData {
		
		private MouseEvent mousePressedEvent;
		private MouseEvent mouseReleasedEvent;
		private MouseEvent mouseMovedEvent;
		private MouseEvent mouseDraggedEvent;
		
		
		
		public void flush() {
			setMousePressedEvent(null);
			setMouseReleasedEvent(null);
			setMouseMovedEvent(null);
			setMouseDraggedEvent(null);
		}
		
		public MouseEvent getMousePressedEvent() {
			return mousePressedEvent;
		}
		public void setMousePressedEvent(MouseEvent mousePressedEvent) {
			this.mousePressedEvent = mousePressedEvent;
		}
		public MouseEvent getMouseReleasedEvent() {
			return mouseReleasedEvent;
		}
		public void setMouseReleasedEvent(MouseEvent mouseReleasedEvent) {
			this.mouseReleasedEvent = mouseReleasedEvent;
		}
		public MouseEvent getMouseMovedEvent() {
			return mouseMovedEvent;
		}
		public void setMouseMovedEvent(MouseEvent mouseMovedEvent) {
			this.mouseMovedEvent = mouseMovedEvent;
		}
		public MouseEvent getMouseDraggedEvent() {
			return mouseDraggedEvent;
		}
		public void setMouseDraggedEvent(MouseEvent mouseDraggedEvent) {
			this.mouseDraggedEvent = mouseDraggedEvent;
		}
		
	}
	
	
	private static class KeyData {
		
		
	    private Set<KeyEvent> currentPressedEvents = new HashSet<>();
	    
	    private Set<KeyEvent> currentReleasedEvents = new HashSet<>();

	    public void addKeyPressed(KeyEvent e) {
	            currentPressedEvents.add(e);
	    }
	    
	    public void addKeyReleased(KeyEvent e) {
			currentReleasedEvents.add(e);
	    }
	    
	    
	    
	    public void flush() {
	        currentPressedEvents.clear();
	        currentReleasedEvents.clear();
	    }

	    public Set<KeyEvent> getCurrentlyPressed() {
	    	return currentPressedEvents;

		}
	    
	    public Set<KeyEvent> getCurrentlyReleased() {
	    	return currentReleasedEvents;

		}


	   
	}

}
