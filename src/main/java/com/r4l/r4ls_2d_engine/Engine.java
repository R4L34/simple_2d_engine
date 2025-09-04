package com.r4l.r4ls_2d_engine;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.r4l.r4ls_2d_engine.event.EventBus;
import com.r4l.r4ls_2d_engine.event.listeners.MouseEventHandler;
import com.r4l.r4ls_2d_engine.gui.Screen;
import com.r4l.r4ls_2d_engine.util.Reference;

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
		Engine.EVENT_BUS.register(new MouseEventHandler());
	}
	
	
	private void windowManager() {
		window = new JFrame();
        Reference.SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
        
        for(int i = 1; Reference.BASE.width * i <= Reference.SCREEN_SIZE.width && Reference.BASE.height * i <= Reference.SCREEN_SIZE.height; i++) {
        	Reference.SCALE = i;
        }
        
        window = new JFrame();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        //Make Fullscreen
        if(Reference.BASE.width * Reference.SCALE == Reference.SCREEN_SIZE.width && Reference.BASE.height * Reference.SCALE == Reference.SCREEN_SIZE.height) {
        	Reference.isFULLSCREEN = true;
        	window.setUndecorated(true); // No title bar or borders
        }
	}
	
	public void drawScreen(Screen screen) {
        if (currentScreen != null) {
            currentScreen.onClose();
            window.remove(currentScreen);
        }

        currentScreen = screen;
        
        Dimension size = new Dimension(Reference.BASE.width * Reference.SCALE, Reference.BASE.height * Reference.SCALE);
        Reference.SIZE = size;
        currentScreen.setPreferredSize(size);

        window.add(currentScreen);
        window.pack();
        
        //
        if(Reference.isFULLSCREEN) {
        	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	        gd.setFullScreenWindow(window); // This makes it truly fullscreen (borderless)
        }else {
        	window.setVisible(true);
        }
        

        currentScreen.onOpen();
        currentScreen.startUpdate();
    }




}
