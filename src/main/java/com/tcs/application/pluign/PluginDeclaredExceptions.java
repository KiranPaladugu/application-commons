/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class PluginDeclaredExceptions implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @XmlElement(name="exceptions")
    private Set<PluginDeclaredException> exceptionsDeclared = new HashSet<>();

    public synchronized Set<PluginDeclaredException> getExceptionsDeclared() {
        return exceptionsDeclared;
    }

    public synchronized void setExceptionsDeclared(Set<PluginDeclaredException> exceptionsDeclared) {
        this.exceptionsDeclared = exceptionsDeclared;
    }

    public void addExcepitonDeclared(PluginDeclaredException declaredException) {
        this.exceptionsDeclared.add(declaredException);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exceptionsDeclared == null) ? 0 : exceptionsDeclared.hashCode());
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
        PluginDeclaredExceptions other = (PluginDeclaredExceptions) obj;
        if (exceptionsDeclared == null) {
            if (other.exceptionsDeclared != null)
                return false;
        } else if (!exceptionsDeclared.equals(other.exceptionsDeclared))
            return false;
        return true;
    }
    
    

}
