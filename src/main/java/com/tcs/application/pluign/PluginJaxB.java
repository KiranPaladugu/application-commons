/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.File;

import javax.xml.bind.JAXB;

import com.tcs.application.pluign.PluginMethodParam.ParamTypes;

public class PluginJaxB {
    public static void main(String[] args){
        PluginDataObject obj = new PluginDataObject("myPlugin", "AddOn");
        obj.setActivatorMethod("activate");
        obj.setClassName("com.bluff.ClassName");
        obj.setDeActivatorMethod("deactivate");
        obj.setVersion("1.0.0");
        obj.setIdentifier("PlginIdentificatioon");
        PluginMethod method = new PluginMethod();
        PluginDeclaredExceptions excepitons = new PluginDeclaredExceptions();
        excepitons.addExcepitonDeclared(new PluginDeclaredException("SomeException"));;
        method.setExceptions(excepitons);
        PluginMethodInputParam input = new PluginMethodInputParam();
        input.addInputParam(new PluginMethodParam("java.lang.String", ParamTypes.Object));
        method.setInput(input);
        PluginMethodOutputParam outparam = new PluginMethodOutputParam();
        outparam.setReturnType(new PluginMethodParam("java.lang.Integer", ParamTypes.Object));
        method.setOutput(outparam);
        PluginMethods methods = new PluginMethods();
        methods.addMethod(method);
        obj.setMethods(methods);
        
        PluginDependencies dependencies = new PluginDependencies();
        dependencies.addDependency(new PluginDependency("SomeName", "SomeIdentification", "someLoaction"));
        obj.setDependencies(dependencies);
        obj.setId("SomUUID");
        JAXB.marshal(obj, new File("C:\\Users\\ekirpal\\Desktop\\Sample.xml"));
    }
}
