package com.r4l.r4ls_2d_engine.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import com.r4l.r4ls_2d_engine.Engine;
import com.r4l.r4ls_2d_engine.ecs.ECS;
import com.r4l.r4ls_2d_engine.ecs.Entity;
import com.r4l.r4ls_2d_engine.ecs.systems.RenderSystem;
import com.r4l.r4ls_2d_engine.event.events.EngineMouseEvent;
import com.r4l.r4ls_2d_engine.util.Loop;

@SuppressWarnings("serial")
public abstract class Screen extends JPanel{
	
	protected ECS ecs;
	
	protected RenderSystem renderSystem;
	
	private Loop gameLoop;
	
	private Screen self = this;

	public Screen() {
		ecs = new ECS();
		renderSystem = new RenderSystem(ecs);
		addMouseListeners();
		Init();
	}
	
	/*
	 * Executes ones before the Screen is Created;
	 */
	public void Init() {
		
	}
	
	/*
	 * Executes ones when the Screen is Created;
	 */
	public void onOpen() {
		repaint();
	}
	
	/*
	 * Executes every frame, afrter onOpen();
	 */
	public void Update() {
		
	}
	
	/*
	 * Executes before a Screen is closed;
	 */
	public void onClose() {
		gameLoop.destroyLoop();
	}
	
	/*
	 * Executes every frame. Graphics Handler;
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		renderSystem.renderAllRenderable(g2);
		g2.dispose();
	}
	
	public void onClick(Entity e) {
		
	}
	
	public void onPress(Entity e) {
		
	}
	
	public void onRelease(Entity e) {
		
	}
	
	public void onHover(Entity e) {
		
	}
	
	public void onUnHover(Entity e) {
		
	}
	
	
	
	/////////////////// Technical //////////////////////
	
	
	public void startUpdate() {
		gameLoop = new Loop("ScreenLoop", () -> {
			
			Update();
			//Executes paintComponent
			repaint();
			
		});
	}
	
	
	public ECS getEcs() {
		return ecs;
	}


	public void setEcs(ECS ecs) {
		this.ecs = ecs;
	}

	public Loop getGameLoop() {
		return gameLoop;
	}

	public RenderSystem getRenderSystem() {
		return renderSystem;
	}
	
	
	private void addMouseListeners() {
		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Engine.EVENT_BUS.post(new EngineMouseEvent.Clicked(e, self));
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            	Engine.EVENT_BUS.post(new EngineMouseEvent.Pressed(e, self));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	Engine.EVENT_BUS.post(new EngineMouseEvent.Released(e, self));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	Engine.EVENT_BUS.post(new EngineMouseEvent.Entered(e, self));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	Engine.EVENT_BUS.post(new EngineMouseEvent.Exited(e, self));
            }
        });

        // Mouse motion (dragging & moving)
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
            	Engine.EVENT_BUS.post(new EngineMouseEvent.Moved(e, self));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            	Engine.EVENT_BUS.post(new EngineMouseEvent.Dragged(e, self));
            }
        });
	}
	

}
