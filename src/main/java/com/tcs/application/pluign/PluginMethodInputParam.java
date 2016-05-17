/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PluginMethodInputParam implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<PluginMethodParam> inputParams = new ArrayList<>();

    public synchronized List<PluginMethodParam> getInputParams() {
        return inputParams;
    }

    public synchronized void setInputParams(List<PluginMethodParam> inputParams) {
        this.inputParams = inputParams;
    }

    public void addInputParam(PluginMethodParam param) {
        this.inputParams.add(param);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((inputParams == null) ? 0 : inputParams.hashCode());
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
        PluginMethodInputParam other = (PluginMethodInputParam) obj;
        if (inputParams == null) {
            if (other.inputParams != null)
                return false;
        } else if (!inputParams.equals(other.inputParams))
            return false;
        return true;
    }
    
    
}
