/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.lang.reflect.*;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.NullType;

public class MethodInvoker implements Runnable {
        
    public Object invokeTheMethod(Object object,  String methodName, Object... parameters)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchObjectException {
        if(object == null){
            throw new IllegalArgumentException(NullType.class.getSimpleName());
        }
        Class<?> type = object.getClass();
        List<Method> availableMethods = getAvailableMethods(type, methodName);
        Method matchingMethod = findMatchingMethod(availableMethods, parameters);        
        if (matchingMethod != null) {
            matchingMethod.setAccessible(true);
            Object returnType = matchingMethod.invoke(object, parameters);
            return returnType;
        }else{
            throw new NoSuchMethodError(methodName + parameters );
        }
//        return null;
    }

    /**
     * @param availableMethods
     * @param parameters
     */
    private Method findMatchingMethod(List<Method> availableMethods, Object[] parameters) {
//        System.out.println("findMatchingMethod():"+availableMethods);
//        System.out.println("findMatchingMethod():"+parameters);
        Method matchingMethod = null;
        int parameterCount = 0;
        if (parameters != null) {
            parameterCount = parameters.length;
        }
        Class<?>[] givenParamTypes = getParamTypes(parameters);
        for (Method method : availableMethods) {
            Class<?>[] types = method.getParameterTypes();
            if (types.length == parameterCount) {
                if (parameterMatch(types, givenParamTypes)) {
                    matchingMethod = method;
                    break;
                }
            }
        }
        return matchingMethod;
    }

    /**
     * @param types
     * @param givenParamTypes
     * @return
     */
    private boolean parameterMatch(Class<?>[] types, Class<?>[] givenParamTypes) {
//        System.out.println("parameterMatch(): with types :"+types);
//        System.out.println("parameterMatch():"+givenParamTypes);
        int parameterCount = 0;
        if (givenParamTypes != null) {
            parameterCount = givenParamTypes.length;
        }
        if (types.length == 0 && parameterCount == 0) {
            return true;
        }
        for (int i = 0; i < types.length; i++) {
            System.out.println("Comparing paramTypes");
            if (types[i].equals(givenParamTypes[i]) || primitiveCheck(types[i], givenParamTypes[i])) {
                continue;
            } else {
                System.err.println(String.format("[INFO] => "+"Check failed for type %s , with param %s",types[i].getName(),givenParamTypes[i].getName()));
                return false;
            }
        }
        return true;
    }
    
    private boolean primitiveCheck(Class<?> type , Class<?> param){
        System.out.println("primitive check ():");
        if(param.equals(Boolean.class)){
            if(type.equals(boolean.class)){
                return true;
            }
        }else if(param.equals(Integer.class)){
            if(type.getName().equals(int.class)){
                return true;
            }
        }else if(param.equals(Long.class)){
            if(type.equals(long.class)){
                return true;
            }
        }else if(param.equals(Float.class)){
            if(type.equals(float.class)){
                return true;
            }
        }else if(param.equals(Double.class)){
            if(type.equals(double.class)){
                return true;
            }
        }else if(param.equals(Byte.class)){
            if(type.equals(byte.class)){
                return true;
            }
        } else if(param.equals(Short.class)){
            if(type.equals(short.class)){
                return true;
            }
        } else if(param.equals(Character.class)){
            if(type.equals(char.class)){
                return true;
            }
        }
        
        
        return false;
    }

    /**
     * @param parameters
     * @return
     */
    private Class<?>[] getParamTypes(Object[] parameters) {
//        System.out.println("getParamTypes():"+parameters);
        Class<?>[] paraTypes = null;
        if (parameters != null) {
            paraTypes = new Class<?>[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                paraTypes[i] = parameters[i].getClass();
            }
        }

        return paraTypes;
    }

    List<Method> getAvailableMethods(Class<?> type, String methodName) {
//        System.out.println("getAvailableMethods:"+type);
//        System.out.println("getAvailableMethods:"+methodName);
        List<Method> list = new ArrayList<>();
        Method[] allMethods = type.getMethods();
        for (Method method : allMethods) {
            if (method.getName().equals(methodName)) {
                list.add(method);
            }
        }
        return list;
    }

    @Override
    public void run() {

    }
    
    public <T> T instance (Class<T> className ){
        if(className!=null){
            if(!className.isInterface()){
                try {
                    return className.newInstance();                    
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
        
    }
    
    @SuppressWarnings("unused")
    public <T> T createInstance(Class<T> className,Object...args){
        Constructor<?>[] constructors = className.getConstructors();
        if(!className.isInterface()){
            
        }
        return null;
    }
    

}
