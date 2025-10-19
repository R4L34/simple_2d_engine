package com.r4l.simple_2d_engine.ecs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Entity {
	
	private String name;
	
	private Set<String> groups;
	
	private List<Component> components;
	
	private ECS ecs;
	
	public Entity(String name) {
		this.name = name;
		this.groups = new HashSet<>();
		components = new ArrayList<>();
	}
	
	public Entity(String name, String group) {
		this.name = name;
		this.groups = new HashSet<>();
		groups.add(group);
		components = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
	////////////// GetComponent (single) //////////////
	
	// By type
	public <T extends Component> T GetComponent(Class<T> type) {
		if (!hasComponent(type)) {
			System.out.println("Error: Entity '" + this.name + "' does not have a component of type '" + type.getSimpleName() + "'");
			return null;
		}
		for (Component c : components) {
			if (type.isInstance(c)) {
				return type.cast(c);
			}
		}
		return null;
	}

	// By name
	@SuppressWarnings("unchecked")
	public <T extends Component> T GetComponent(String name) {
		if (!hasComponent(name)) {
			System.out.println("Error: Entity '" + this.name + "' does not have a component with name '" + name + "'");
			return null;
		}
		for (Component c : components) {
			if (c.getName().equals(name)) {
				return (T) c;
			}
		}
		return null;
	}

	// By group (single group)
	public <T extends Component> T GetComponentByGroup(Class<T> type,String group) {
		if (!hasComponentByGroup(group)) {
			System.out.println("Error: Entity '" + this.name + "' does not have a component in group '" + group + "'");
			return null;
		}
		for (Component c : components) {
			for (String g : c.getGroups()) {
				if (g.equals(group) && type.isInstance(c)) {
					return type.cast(c);
				}
			}
		}
		return null;
	}

	// By any group (among multiple)
	public <T extends Component> T GetComponentAmongGroups(Class<T> type, String... groups) {
		if (!hasComponentAmongGroups(groups)) {
			System.out.println("Error: Entity '" + this.name + "' does not have a component among given groups");
			return null;
		}
		for (Component c : components) {
			for (String g : c.getGroups()) {
				for (String target : groups) {
					if (g.equals(target) && type.isInstance(c)) {
						return type.cast(c);
					}
				}
			}
		}
		return null;
	}

	// By all groups (must contain all)
	public <T extends Component> T GetComponentWithAllGroups(Class<T> type, String... groups) {
		if (!hasComponentWithAllGroups(groups)) {
			System.out.println("Error: Entity '" + this.name + "' does not have a component with all required groups");
			return null;
		}
		for (Component c : components) {
			int count = 0;
			for (String g : c.getGroups()) {
				for (String target : groups) {
					if (g.equals(target)) {
						count++;
					}
				}
			}
			if (count == groups.length && groups.length != 0 && type.isInstance(c)) {
				return type.cast(c);
			}
		}
		return null;
	}
	
	
	////////////// GetComponents (multiple) //////////////
	
	// By type
	public <T extends Component> List<T> GetComponents(Class<T> type) {
		List<T> results = new ArrayList<>();
		for (Component c : components) {
			if (type.isInstance(c)) {
				results.add(type.cast(c));
			}
		}
		return results;
	}
	
	// By group (single group)
	public <T extends Component> List<T> GetComponentsByGroup(Class<T> type, String group) {
		List<T> results = new ArrayList<>();
		for (Component c : components) {
			for (String g : c.getGroups()) {
				if (g.equals(group) && type.isInstance(c)) {
					results.add(type.cast(c));
					break;
				}
			}
		}
		return results;
	}
	
	// By any group (among multiple)
	public <T extends Component> List<T> GetComponentsAmongGroups(Class<T> type, String... groups) {
		List<T> results = new ArrayList<>();
		for (Component c : components) {
			for (String g : c.getGroups()) {
				for (String target : groups) {
					if (g.equals(target) && type.isInstance(c)) {
						results.add(type.cast(c));
						break;
					}
				}
			}
		}
		return results;
	}
	
	// By all groups (must contain all)
	public <T extends Component> List<T> GetComponentsWithAllGroups(Class<T> type, String... groups) {
		List<T> results = new ArrayList<>();
		for (Component c : components) {
			int count = 0;
			for (String g : c.getGroups()) {
				for (String target : groups) {
					if (g.equals(target)) {
						count++;
					}
				}
			}
			if (count == groups.length && groups.length != 0 && type.isInstance(c)) {
				results.add(type.cast(c));
			}
		}
		return results;
	}
	
	
	////////////// HasComponents //////////////
	
	public boolean hasComponent(Class<? extends Component> type) {	
		for (Component c : components) {
			if (type.isInstance(c)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasComponent(String name) {
		for (Component c : components) {
			if (c.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasComponentByGroup(String group) {
		for (Component c : components) {
			for(String groups : c.getGroups()) {
				if(groups.equals(group)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasComponentAmongGroups(String... group) {
		for (Component c : components) {
			for(String groups : c.getGroups()) {
				for(String g : group) {
					if(groups.equals(g)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean hasComponentWithAllGroups(String... group) {
		for (Component c : components) {
			for(String groups : c.getGroups()) {
				int count1 = 0;
				for(String g : group) {
					if(groups.equals(g)) {
						count1++;
					}
				}
				if(count1 == group.length && group.length != 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public void addComponent(Component c) {
		if (hasComponent(name)) {
			System.out.println("Couldn't add component, name has already been taken! " + name);
			return;
		}
		c.setEntity(this);
		components.add(c);
	}
	
	public void flush() {
		components = new ArrayList<>();
	}
	
	public void flushByGroup(String group) {
	    components.removeIf(c -> c.getGroups().contains(group));
	}

	public ECS getECS() {
		return ecs;
	}

	public void setECS(ECS ecs) {
		this.ecs = ecs;
	}
	
	public List<Component> getComponentList() {
		return components;
	}
	
}
