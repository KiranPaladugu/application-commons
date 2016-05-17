/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PluginDependencies implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Set<PluginDependency> dependencies = new HashSet<>();

    public synchronized Set<PluginDependency> getDependencies() {
        return dependencies;
    }

    public synchronized void setDependencies(Set<PluginDependency> dependencies) {
        this.dependencies = dependencies;
    }
    
    public synchronized void addDependency(PluginDependency dependency){
        this.dependencies.add(dependency);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dependencies == null) ? 0 : dependencies.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PluginDependencies other = (PluginDependencies) obj;
        if (dependencies == null) {
            if (other.dependencies != null)
                return false;
        } else if (!dependencies.equals(other.dependencies))
            return false;
        return true;
    }
    
}
