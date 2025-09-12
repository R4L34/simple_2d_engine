package com.r4l.simple_2d_engine.ecs;

import java.util.ArrayList;
import java.util.List;

import com.r4l.simple_2d_engine.ecs.components.HitboxComponent;
import com.r4l.simple_2d_engine.gui.Screen;

public class ECS {
	
	private List<Entity> entityList;
	
	private Screen screen;
	
	public ECS() {
		this.entityList = new ArrayList<>();
	}
	
	public ECS(Screen screen) {
		this.entityList = new ArrayList<>();
		setScreen(screen);
	}
	

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void emptyEntityList() {
		this.entityList = new ArrayList<>();
	}
	
//////////////HasEntities //////////////
	
public boolean hasEntity(String name) {
	for (Entity e : entityList) {
		if (e.getName().equals(name)) {
			return true;
		}
	}
	return false;
}

public boolean hasEntity(Class<? extends Entity> type) {
	for (Entity e : entityList) {
		if (type.isInstance(e)) {
			return true;
		}
	}
	return false;
}

public boolean hasEntityByGroup(String group) {
	for (Entity e : entityList) {
		for (String g : e.getGroups()) {
			if (g.equals(group)) {
				return true;
			}
		}
	}
	return false;
}

public boolean hasEntityAmongGroups(String... groups) {
	for (Entity e : entityList) {
		for (String g : e.getGroups()) {
			for (String target : groups) {
				if (g.equals(target)) {
					return true;
				}
			}
		}
	}
	return false;
}

public boolean hasEntityWithAllGroups(String... groups) {
	for (Entity e : entityList) {
		int count = 0;
		for (String g : e.getGroups()) {
			for (String target : groups) {
				if (g.equals(target)) {
					count++;
				}
			}
		}
		if (count == groups.length && groups.length != 0) {
			return true;
		}
	}
	return false;
}


////////////// GetEntity (single) //////////////

@SuppressWarnings("unchecked")
public <T extends Entity> T GetEntity(String name) {
	if (!hasEntity(name)) {
		System.out.println("Error: No Entity with name '" + name + "' found.");
		return null;
	}
	for (Entity e : entityList) {
		if (e.getName().equals(name)) {
			return (T) e;
		}
	}
	return null;
}

public <T extends Entity> T GetEntity(Class<T> type) {
	if (!hasEntity(type)) {
		System.out.println("Error: No Entity of type '" + type.getSimpleName() + "' found.");
		return null;
	}
	for (Entity e : entityList) {
		if (type.isInstance(e)) {
			return type.cast(e);
		}
	}
	return null;
}

public <T extends Entity> T GetEntityByGroup(Class<T> type, String group) {
	if (!hasEntityByGroup(group)) {
		System.out.println("Error: No Entity in group '" + group + "' found.");
		return null;
	}
	for (Entity e : entityList) {
		for (String g : e.getGroups()) {
			if (g.equals(group) && type.isInstance(e)) {
				return type.cast(e);
			}
		}
	}
	return null;
}

public <T extends Entity> T GetEntityAmongGroups(Class<T> type, String... groups) {
	if (!hasEntityAmongGroups(groups)) {
		System.out.println("Error: No Entity found among provided groups.");
		return null;
	}
	for (Entity e : entityList) {
		for (String g : e.getGroups()) {
			for (String target : groups) {
				if (g.equals(target) && type.isInstance(e)) {
					return type.cast(e);
				}
			}
		}
	}
	return null;
}

public <T extends Entity> T GetEntityWithAllGroups(Class<T> type, String... groups) {
	if (!hasEntityWithAllGroups(groups)) {
		System.out.println("Error: No Entity found with all provided groups.");
		return null;
	}
	for (Entity e : entityList) {
		int count = 0;
		for (String g : e.getGroups()) {
			for (String target : groups) {
				if (g.equals(target)) {
					count++;
				}
			}
		}
		if (count == groups.length && groups.length != 0 && type.isInstance(e)) {
			return type.cast(e);
		}
	}
	return null;
}

////////////// GetEntities (multiple) //////////////

public <T extends Entity> List<T> GetEntities(Class<T> type) {
	List<T> results = new ArrayList<>();
	for (Entity e : entityList) {
		if (type.isInstance(e)) {
			results.add(type.cast(e));
		}
	}
	return results;
}

public <T extends Entity> List<T> GetEntitiesByGroup(Class<T> type, String group) {
	List<T> results = new ArrayList<>();
	for (Entity e : entityList) {
		for (String g : e.getGroups()) {
			if (g.equals(group) && type.isInstance(e)) {
				results.add(type.cast(e));
				break;
			}
		}
	}
	return results;
}

public <T extends Entity> List<T> GetEntitiesAmongGroups(Class<T> type, String... groups) {
	List<T> results = new ArrayList<>();
	for (Entity e : entityList) {
		for (String g : e.getGroups()) {
			for (String target : groups) {
				if (g.equals(target) && type.isInstance(e)) {
					results.add(type.cast(e));
					break;
				}
			}
		}
	}
	return results;
}

public <T extends Entity> List<T> GetEntitiesWithAllGroups(Class<T> type, String... groups) {
	List<T> results = new ArrayList<>();
	for (Entity e : entityList) {
		int count = 0;
		for (String g : e.getGroups()) {
			for (String target : groups) {
				if (g.equals(target)) {
					count++;
				}
			}
		}
		if (count == groups.length && groups.length != 0 && type.isInstance(e)) {
			results.add(type.cast(e));
		}
	}
	return results;
}
	
	public void addEntity(Entity e) {
		if(hasEntity(e.getName())) {
			System.out.println("Couldn't add Entity, name has already been taken! " + e.getName());
			return;
		}
		e.setECS(this);
		entityList.add(e);
		//Initialise hitboxes
		if (e.hasComponent(HitboxComponent.class)) {
			for(HitboxComponent hitbox : e.GetComponents(HitboxComponent.class)) {
				hitbox.initialise();
			}
		}
	}


	public Screen getScreen() {
		return screen;
	}


	public void setScreen(Screen screen) {
		this.screen = screen;
	}

}
