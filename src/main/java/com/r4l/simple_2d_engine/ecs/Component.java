package com.r4l.simple_2d_engine.ecs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class Component {
	
	private String name;
	
	private Set<String> groups;
	
	private Entity entity;
	
	private final List<Class<? extends Component>> dependencies;
	
	public Component(String name) {
		this.name = name;
		dependencies = new ArrayList<>();
	}
	
	public Component(String name, String group) {
		this.name = name;
		this.groups = new HashSet<>();
		groups.add(name);
		dependencies = new ArrayList<>();
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
	
	/**
     * Adds a dependency on another component class.
     * Ignores nulls and duplicates.
     */
    public void addDependency(Class<? extends Component> dependency) {
        Objects.requireNonNull(dependency, "dependency cannot be null");
        if (!dependencies.contains(dependency)) {
            dependencies.add(dependency);
        }
    }

    /**
     * Removes a dependency on another component class.
     */
    public void removeDependency(Class<? extends Component> dependency) {
        dependencies.remove(dependency);
    }
}
