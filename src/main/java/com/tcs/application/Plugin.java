/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public interface Plugin {
    public void setPlugId(String id);
    public String getPlugId();
    public void setName(String name);
    public String getName();
    public void setIdentifier(String identifier);
    public String getIdentifier();
    public String getVersion();
    public String getClassName();
    public String getActivatorMethod();
    public String getDeActivatorMethod();
    
    public void setVersion(String version);
    public void setActivatorMethod(String activatorMethod);
    public void setDeActivatorMethod(String deactivatorMethod);
}
