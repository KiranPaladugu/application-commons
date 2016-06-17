/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;

import com.tcs.application.Plugin;

public class ManagablePlugin implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String id;
    private String version;
    private String identifier;
    private String className;
    private String activatorMethod;
    private String deactivatorMethod;
    public String getName() {
        return name;
    }
    
    public synchronized static ManagablePlugin getMO(Plugin plugin){
        ManagablePlugin mo = new ManagablePlugin();
        mo.setName(plugin.getName());
        mo.setId(plugin.getPlugId());
        mo.setIdentifier(plugin.getIdentifier());
        mo.setVersion(plugin.getVersion());
        mo.setClassName(plugin.getClassName());
        mo.setActivatorMethod(plugin.getActivatorMethod());
        mo.setDeactivatorMethod(plugin.getDeActivatorMethod());
        return mo;
        
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getActivatorMethod() {
        return activatorMethod;
    }
    public void setActivatorMethod(String activatorMethod) {
        this.activatorMethod = activatorMethod;
    }
    public String getDeactivatorMethod() {
        return deactivatorMethod;
    }
    public void setDeactivatorMethod(String deactivatorMethod) {
        this.deactivatorMethod = deactivatorMethod;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activatorMethod == null) ? 0 : activatorMethod.hashCode());
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        result = prime * result + ((deactivatorMethod == null) ? 0 : deactivatorMethod.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        ManagablePlugin other = (ManagablePlugin) obj;
        if (activatorMethod == null) {
            if (other.activatorMethod != null)
                return false;
        } else if (!activatorMethod.equals(other.activatorMethod))
            return false;
        if (className == null) {
            if (other.className != null)
                return false;
        } else if (!className.equals(other.className))
            return false;
        if (deactivatorMethod == null) {
            if (other.deactivatorMethod != null)
                return false;
        } else if (!deactivatorMethod.equals(other.deactivatorMethod))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }
    
    

}
