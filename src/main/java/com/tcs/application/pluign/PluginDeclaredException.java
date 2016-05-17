/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;

public class PluginDeclaredException implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String exceptionClass;

    
    /**
     * @param exceptionClass
     */
    public PluginDeclaredException(String exceptionClass) {
        super();
        this.exceptionClass = exceptionClass;
    }

    public synchronized String getExceptionClass() {
        return exceptionClass;
    }

    public synchronized void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exceptionClass == null) ? 0 : exceptionClass.hashCode());
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
        PluginDeclaredException other = (PluginDeclaredException) obj;
        if (exceptionClass == null) {
            if (other.exceptionClass != null)
                return false;
        } else if (!exceptionClass.equals(other.exceptionClass))
            return false;
        return true;
    }
    
    
}
