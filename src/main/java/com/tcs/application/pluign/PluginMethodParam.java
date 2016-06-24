/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class PluginMethodParam implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public enum ParamTypes {
        Array, Object, Void
    };

    @XmlElement(name="paramClass")
    private String paramClass;
    @XmlElement(name="param-type")
    private ParamTypes paramType;
   
    /**
     * 
     */
    public PluginMethodParam() {
    }
    
    /**
     * @param paramClass
     * @param paramType
     */
    public PluginMethodParam(String className, ParamTypes type) {
        super();
        this.paramClass = className;
        this.paramType = type;
    }
    public synchronized String getParamClass() {
        return paramClass;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((paramClass == null) ? 0 : paramClass.hashCode());
        result = prime * result + ((paramType == null) ? 0 : paramType.hashCode());
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
        PluginMethodParam other = (PluginMethodParam) obj;
        if (paramClass == null) {
            if (other.paramClass != null)
                return false;
        } else if (!paramClass.equals(other.paramClass))
            return false;
        if (paramType != other.paramType)
            return false;
        return true;
    }
    public synchronized void setClassName(String className) {
        this.paramClass = className;
    }
    public synchronized ParamTypes getParamType() {
        return paramType;
    }
    public synchronized void setType(ParamTypes type) {
        this.paramType = type;
    }
    
    
}
