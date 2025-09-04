package com.r4l.r4ls_2d_engine.ecs;

import java.util.HashSet;
import java.util.Set;

public abstract class Component {
	
	private String name;
	
	private Set<String> groups;
	
	private Entity entity;
	
	public Component(String name) {
		this.name = name;
	}
	
	public Component(String name, String group) {
		this.name = name;
		this.groups = new HashSet<>();
		groups.add(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public void addGroup(String group) {
		groups.add(group);
	}
	
	public void removeGroup(String group) {
		groups.remove(group);
	}
	
	public void flushGroups() {
		groups = new HashSet<>();
	}
	
	public Set<String> getGroups(){
		return groups;
		
	}
}
