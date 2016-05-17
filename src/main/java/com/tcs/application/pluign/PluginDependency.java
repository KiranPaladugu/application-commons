/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;

public class PluginDependency implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String pluginName;
    private String pluginIdentifier;
    private String pluginLocation;
    
    /**
     * 
     */
    public PluginDependency() {
    }
    
    /**
     * @param pluginName
     * @param pluginIdentifier
     * @param pluginLocation
     */
    public PluginDependency(String pluginName, String pluignIdentifier, String pluginLocation) {
        super();
        this.pluginName = pluginName;
        this.pluginIdentifier = pluignIdentifier;
        this.pluginLocation = pluginLocation;
    }
    public synchronized String getPluginName() {
        return pluginName;
    }
    public synchronized void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }
    public synchronized String getPluginIdentifier() {
        return pluginIdentifier;
    }
    public synchronized void setPluginIdentifier(String pluignIdentifier) {
        this.pluginIdentifier = pluignIdentifier;
    }
    public synchronized String getPluginLocation() {
        return pluginLocation;
    }
    public synchronized void setPluginLocation(String pluginLocation) {
        this.pluginLocation = pluginLocation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pluginLocation == null) ? 0 : pluginLocation.hashCode());
        result = prime * result + ((pluginName == null) ? 0 : pluginName.hashCode());
        result = prime * result + ((pluginIdentifier == null) ? 0 : pluginIdentifier.hashCode());
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
        PluginDependency other = (PluginDependency) obj;
        if (pluginLocation == null) {
            if (other.pluginLocation != null)
                return false;
        } else if (!pluginLocation.equals(other.pluginLocation))
            return false;
        if (pluginName == null) {
            if (other.pluginName != null)
                return false;
        } else if (!pluginName.equals(other.pluginName))
            return false;
        if (pluginIdentifier == null) {
            if (other.pluginIdentifier != null)
                return false;
        } else if (!pluginIdentifier.equals(other.pluginIdentifier))
            return false;
        return true;
    }
    
    

}
