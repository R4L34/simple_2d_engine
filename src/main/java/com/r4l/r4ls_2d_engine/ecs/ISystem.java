package com.r4l.r4ls_2d_engine.ecs;

import java.util.ArrayList;
import java.util.List;

public abstract class ISystem {

    protected List<Class<? extends Component>> dependencies;

    public ISystem() {
        dependencies = new ArrayList<>();
    }

    /**
     * Adds a dependency type to this system if it's not already present.
     */
    public <T extends Component> void addDependency(Class<T> type) {
        if (!dependencies.contains(type)) {
            dependencies.add(type);
        }
    }
    
    public List<Class<? extends Component>> getDependencies() {
    	return dependencies;
    }
}

