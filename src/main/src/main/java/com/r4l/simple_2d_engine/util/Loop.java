package com.r4l.simple_2d_engine.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Loop {
	
	public static final List<Loop> activeLoops = Collections.synchronizedList(new ArrayList<>());
    public static final List<Loop> stoppedLoops = Collections.synchronizedList(new ArrayList<>());

	
	
	private String name;
	
	Thread thread;
	
	Runnable runnable;
	
    private volatile boolean isActive = true;
	
	@SuppressWarnings("unused")
	public Loop (String name, Runnable runnable) {
		this.runnable = runnable;
		isActive = true;
		this.name = name;
		
		thread = new Thread(() -> {
			double interval = Reference.NANOSECOND / Reference.TICKRATE;
			double delta = 0;
			long lastTime = System.nanoTime();
			long currentTime;
			
			//FpsCounter
			long timer = 0;
			int drawCount = 0;
			
			while (isActive) {
				
				currentTime = System.nanoTime();
				delta += (currentTime - lastTime) / interval;
				timer += (currentTime - lastTime);
				lastTime = currentTime;
				
				
				if (delta >= 1) {
					runnable.run();
					delta--;
					
					drawCount++;
				}
				//Fps Counter
				if(timer >= Reference.NANOSECOND) {
					//System.out.println("Fps: " + drawCount);
					drawCount = 0;
					timer = 0;
				}
				
				
			}
		});
		startLoop();
	}
	

    public void startLoop() {
        if (thread == null || !thread.isAlive()) {
            isActive = true;
            thread.start();
        }
        
        stoppedLoops.remove(this);
        if (!activeLoops.contains(this)) {
            activeLoops.add(this);
        }
    }

    public void stopLoop() {
        isActive = false;
        if (thread != null) {
            thread.interrupt();
        }
        
        activeLoops.remove(this);
        if (!stoppedLoops.contains(this)) {
            stoppedLoops.add(this);
        }
    }

    public void destroyLoop() {
        stopLoop();
        thread = null;
        
        activeLoops.remove(this);
        stoppedLoops.remove(this);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public static void closeAll() {
		for(int i = 0; i < Loop.activeLoops.size(); i++) {
				Loop.activeLoops.get(i).destroyLoop();
		}
		
		for(int i = 0; i < Loop.stoppedLoops.size(); i++) {
				Loop.stoppedLoops.get(i).destroyLoop();
		}
	}

}