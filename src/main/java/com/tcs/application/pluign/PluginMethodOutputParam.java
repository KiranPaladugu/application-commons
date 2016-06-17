/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class PluginMethodOutputParam implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @XmlElement(name="param")
    private PluginMethodParam returnType;

    public synchronized PluginMethodParam getReturnType() {
        return returnType;
    }

    public synchronized void setReturnType(PluginMethodParam returnType) {
        this.returnType = returnType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((returnType == null) ? 0 : returnType.hashCode());
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
        PluginMethodOutputParam other = (PluginMethodOutputParam) obj;
        if (returnType == null) {
            if (other.returnType != null)
                return false;
        } else if (!returnType.equals(other.returnType))
            return false;
        return true;
    }
    
}
