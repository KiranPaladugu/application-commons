/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;

public class PluginDataObject implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String identifier;
    private String pluginType;
    private String className;
    private String methodName;
    private String activatorMethod;
    private String deActivatorMethod;
    private String version;
    private PluginMethods methods;
    private PluginDependencies dependencies;
    private String[] memberNames = { "name", "version", "className", "id" };

    public synchronized String getActivatorMethod() {
        return activatorMethod;
    }

    public synchronized void setActivatorMethod(String activatorMethod) {
        this.activatorMethod = activatorMethod;
    }

    public synchronized String[] getMemberNames() {
        return memberNames;
    }

    /**
     * @param name
     * @param pluginType
     */
    public PluginDataObject(String name, String pluginType) {
        super();
        this.name = name;
        this.pluginType = pluginType;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized String getPluginType() {
        return pluginType;
    }

    public synchronized void setPluginType(String pluginType) {
        this.pluginType = pluginType;
    }

    public synchronized String getClassName() {
        return className;
    }

    public synchronized void setClassName(String className) {
        this.className = className;
    }

    public synchronized String getMethodName() {
        return methodName;
    }

    public synchronized void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public synchronized String getActivatorMethodName() {
        return activatorMethod;
    }

    public synchronized PluginMethods getMethods() {
        return methods;
    }

    public synchronized void setMethods(PluginMethods methods) {
        this.methods = methods;
    }

    public synchronized PluginDependencies getDependencies() {
        return dependencies;
    }

    public synchronized void setDependencies(PluginDependencies dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activatorMethod == null) ? 0 : activatorMethod.hashCode());
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        result = prime * result + ((dependencies == null) ? 0 : dependencies.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + ((methods == null) ? 0 : methods.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pluginType == null) ? 0 : pluginType.hashCode());
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
        PluginDataObject other = (PluginDataObject) obj;
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
        if (dependencies == null) {
            if (other.dependencies != null)
                return false;
        } else if (!dependencies.equals(other.dependencies))
            return false;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (methodName == null) {
            if (other.methodName != null)
                return false;
        } else if (!methodName.equals(other.methodName))
            return false;
        if (methods == null) {
            if (other.methods != null)
                return false;
        } else if (!methods.equals(other.methods))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (pluginType == null) {
            if (other.pluginType != null)
                return false;
        } else if (!pluginType.equals(other.pluginType))
            return false;
        return true;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeActivatorMethod() {
        return deActivatorMethod;
    }

    public void setDeActivatorMethod(String deActivatorMethod) {
        this.deActivatorMethod = deActivatorMethod;
    }

}
