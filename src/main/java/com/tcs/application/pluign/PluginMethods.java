/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;
import java.util.*;

public class PluginMethods implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Set<PluginMethod> methods = new HashSet<>();

    public synchronized Set<PluginMethod> getMethods() {
        return methods;
    }

    public synchronized void setMethods(Set<PluginMethod> methods) {
        this.methods = methods;
    }
    
    public synchronized void addMethod(PluginMethod method){
        this.methods.add(method);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((methods == null) ? 0 : methods.hashCode());
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
        PluginMethods other = (PluginMethods) obj;
        if (methods == null) {
            if (other.methods != null)
                return false;
        } else if (!methods.equals(other.methods))
            return false;
        return true;
    }
    
    
}
