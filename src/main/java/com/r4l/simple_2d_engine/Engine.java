package com.r4l.simple_2d_engine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.r4l.simple_2d_engine.event.EventBus;
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
	}
	
	
	private void windowManager() {
	    window = new JFrame();
	    Reference.SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	    int scaleX = Reference.SCREEN_SIZE.width / Reference.BASE_RESOLUTION.width;
	    int scaleY = Reference.SCREEN_SIZE.height / Reference.BASE_RESOLUTION.height;
	    if(Reference.SCALE == 0)
	    	Reference.SCALE = Math.min(scaleX, scaleY);

	    if(Reference.isFULLSCREEN) {
	    	window.setUndecorated(true);
	    } else {
	    	window.setUndecorated(false);
	    }
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setResizable(false);
	    window.getContentPane().setBackground(Color.BLACK);
	}




	
	public void drawScreen(Screen screen) {
	    if (currentScreen != null) {
	        currentScreen.onClose();
	        window.remove(currentScreen);
	    }

	    currentScreen = screen;
	    Dimension size = null;
	    if(Reference.FINAL_RESOLUTION == null) {
	    	size = new Dimension(
	    	        Reference.BASE_RESOLUTION.width * Reference.SCALE,
	    	        Reference.BASE_RESOLUTION.height * Reference.SCALE
	    	    );
	    	    Reference.FINAL_RESOLUTION = size;
	    }else {
	    	size = Reference.FINAL_RESOLUTION;
	    }
	    
	    
	    
	    
	    currentScreen.setPreferredSize(size);

	    window.add(currentScreen);
	    window.pack();

	    if (Reference.isFULLSCREEN) {
	        // Borderless fullscreen window
	        window.setBounds(0, 0, Reference.SCREEN_SIZE.width, Reference.SCREEN_SIZE.height);
	        window.setVisible(true);

	        // Center the scaled screen inside the window → black bars
	        int offsetX = (Reference.SCREEN_SIZE.width - size.width) / 2;
	        int offsetY = (Reference.SCREEN_SIZE.height - size.height) / 2;
	        currentScreen.setBounds(offsetX, offsetY, size.width, size.height);

	    } else {
	        window.setVisible(true);
	    }

	    currentScreen.onOpen();
	    currentScreen.startUpdate();
	}







}
