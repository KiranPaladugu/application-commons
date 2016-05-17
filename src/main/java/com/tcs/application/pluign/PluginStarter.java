/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import com.tcs.application.*;

public class PluginStarter implements Subscriber {

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.SubscriptionEvent)
     */
    @Override
    public void onSubscriptionEvent(SubscriptionEvent event) {

    }

    /**
     * 
     */
    public void startPluin(PluginDataObject pluginDataObject) {
        try {
            String className = pluginDataObject.getClassName();
            String method = pluginDataObject.getActivatorMethod();
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class<?> loadedClass = classLoader.loadClass(className);
            Object obj = loadedClass.newInstance();
            MethodInvoker invoker = new MethodInvoker();
            invoker.invokeTheMethod(obj, method);

        } catch (Exception e) {
            e.printStackTrace();
            Application.getSubscriptionManager().notifySubscriber(PluginManager.PLUGIN_START_FAIL, this, pluginDataObject);
        }
    }

    public void stopPlugin(PluginDataObject pluignObject) {
        try {
            String className = pluignObject.getClassName();
            String method = pluignObject.getDeActivatorMethod();
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class<?> loadedClass = classLoader.loadClass(className);
            Object obj = loadedClass.newInstance();
            MethodInvoker invoker = new MethodInvoker();
            invoker.invokeTheMethod(obj, method);

        } catch (Exception e) {
            e.printStackTrace();
            Application.getSubscriptionManager().notifySubscriber(PluginManager.PLUGIN_STOP_FAIL, this, pluignObject);
        }
    }

}
