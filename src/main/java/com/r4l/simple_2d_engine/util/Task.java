package com.r4l.simple_2d_engine.util;

public class Task implements Runnable{
	
	private String name;
	
	private Runnable task;
	
	public Task (String name, Runnable task) {
		setName(name);
		setTask(task);
	}

	@Override
	public void run() {
		
		task.run();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Runnable getTask() {
		return task;
	}

	public void setTask(Runnable task) {
		this.task = task;
	}

}
