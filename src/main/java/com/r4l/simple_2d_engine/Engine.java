package com.r4l.simple_2d_engine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import com.r4l.simple_2d_engine.event.EventBus;
import com.r4l.simple_2d_engine.event.listeners.KeyHandler;
import com.r4l.simple_2d_engine.event.listeners.MouseHandler;
import com.r4l.simple_2d_engine.gui.Screen;
import com.r4l.simple_2d_engine.util.Reference;


public class Engine {
	
	private static Engine instance;
	
	public static EventBus EVENT_BUS = new EventBus();
	
	private JFrame window;
	
	private Screen currentScreen;
	
	private Engine() {
		registerEventListeners();
		windowManager();
	}
	
	/*
	 * Returns engine instance!
	 */
	public static Engine getInstance() {
		if(instance == null) {
			instance = new Engine();
		}
		return instance;
	}
	
	
	private void registerEventListeners() {
		Engine.EVENT_BUS.register(new MouseHandler());
		Engine.EVENT_BUS.register(new KeyHandler());
	}
	
	
	private void windowManager(){
		if (window != null) {
			window.dispose();
		}
	    window = new JFrame();
	    Reference.SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	    double scaleX = Reference.SCREEN_SIZE.width / (double) Reference.BASE_RESOLUTION.width;
	    double scaleY = Reference.SCREEN_SIZE.height / (double) Reference.BASE_RESOLUTION.height;

	    boolean screenTooSmall = scaleX < 1 || scaleY < 1;

	    // If SCALE = 0, auto-scale to fit screen
	    if (Reference.SCALE == 0) {
	        Reference.SCALE = (int) Math.floor(Math.min(scaleX, scaleY));
	        if (Reference.SCALE < 1) Reference.SCALE = 1; // fallback minimum
	    }

	    // Check if the scaled window exceeds the screen
	    boolean tooWide = Reference.BASE_RESOLUTION.width * Reference.SCALE > Reference.SCREEN_SIZE.width;
	    boolean tooTall = Reference.BASE_RESOLUTION.height * Reference.SCALE > Reference.SCREEN_SIZE.height;

	    // If window doesn't fit with current custom scale
	    if (screenTooSmall && (tooWide || tooTall)) {
	        Reference.SCALE = Reference.DIMINISHING_SCALE;
	    }


	    if(Reference.isFULLSCREEN) {
	    	window.setUndecorated(true);
	    } else {
	    	window.setUndecorated(false);
	    }
	    
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setResizable(false);
	    window.getContentPane().setBackground(Color.BLACK);
	    if(Reference.ICON != null) {
	    	 window.setIconImage(Reference.ICON);
	    }
	}


	
	public void drawScreen(Screen screen) {
	    
	    if (currentScreen != null) {
	        currentScreen.onClose();
	        window.remove(currentScreen);
	    }

	    currentScreen = screen;

	    Dimension size;
	    size = new Dimension(
	    	    (int) (Reference.BASE_RESOLUTION.width * Reference.SCALE),
	    	    (int) (Reference.BASE_RESOLUTION.height * Reference.SCALE)
	    	);
	    if (Reference.FINAL_RESOLUTION == null) {
	    	Reference.FINAL_RESOLUTION = size;
	    }
	    	


	    currentScreen.setPreferredSize(Reference.FINAL_RESOLUTION );
	    currentScreen.setSize(Reference.FINAL_RESOLUTION);

	    window.add(currentScreen);
	    window.revalidate();
	    window.repaint();

	    if (Reference.isFULLSCREEN) {
	        window.setBounds(0, 0, Reference.SCREEN_SIZE.width, Reference.SCREEN_SIZE.height);
	        window.setVisible(true);

	        int offsetX = (Reference.SCREEN_SIZE.width - size.width) / 2;
	        int offsetY = (Reference.SCREEN_SIZE.height - size.height) / 2;
	        currentScreen.setLocation(offsetX, offsetY);

	        window.setLayout(null);
	    } else {
	        window.pack();
	        window.setVisible(true);
	    }

	    currentScreen.onOpen();
	    currentScreen.startUpdate();
	    
	    currentScreen.setFocusable(true);
	    currentScreen.requestFocusInWindow();
	}



	public static void setFullscreen(boolean fullscreen) {
	    Engine engine = getInstance();
	    if (Reference.isFULLSCREEN == fullscreen)
	        return;

	    Reference.isFULLSCREEN = fullscreen;

	    Screen screen = engine.currentScreen;
	    engine.window.setVisible(false);

	    engine.windowManager();

	    if (screen != null) {
	        engine.drawScreen(screen);
	    }
	}
	
	public static boolean setScale(double newScale) {
		if(newScale == Reference.SCALE) {
			return false;
		}
		Reference.FINAL_RESOLUTION = null;
		Engine.getInstance().windowManager();
		Reference.SCALE = newScale;
		return true;
	}


}