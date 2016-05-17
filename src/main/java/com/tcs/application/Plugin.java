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
}
