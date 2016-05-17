/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.Serializable;

public class PluginMethod implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String className;
    private String methodName;
    private PluginMethodInputParam input;
    private PluginMethodOutputParam output;
    private PluginDeclaredExceptions exceptions;

    private String memberNames[] = { "name", "class" };

    /**
     * @param className
     * @param methodName
     */
    public PluginMethod(String className, String methodName) {
        super();
        this.className = className;
        this.methodName = methodName;
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

    public PluginMethodInputParam getInput() {
        return input;
    }

    public void setInput(PluginMethodInputParam input) {
        this.input = input;
    }

    public PluginMethodOutputParam getOutput() {
        return output;
    }

    public void setOutput(PluginMethodOutputParam output) {
        this.output = output;
    }

    public PluginDeclaredExceptions getExceptions() {
        return exceptions;
    }

    public void setExceptions(PluginDeclaredExceptions exceptions) {
        this.exceptions = exceptions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        result = prime * result + ((exceptions == null) ? 0 : exceptions.hashCode());
        result = prime * result + ((input == null) ? 0 : input.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + ((output == null) ? 0 : output.hashCode());
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
        PluginMethod other = (PluginMethod) obj;
        if (className == null) {
            if (other.className != null)
                return false;
        } else if (!className.equals(other.className))
            return false;
        if (exceptions == null) {
            if (other.exceptions != null)
                return false;
        } else if (!exceptions.equals(other.exceptions))
            return false;
        if (input == null) {
            if (other.input != null)
                return false;
        } else if (!input.equals(other.input))
            return false;
        if (methodName == null) {
            if (other.methodName != null)
                return false;
        } else if (!methodName.equals(other.methodName))
            return false;
        if (output == null) {
            if (other.output != null)
                return false;
        } else if (!output.equals(other.output))
            return false;
        return true;
    }

    public String[] getMemberNames() {
        return memberNames;
    }

}
