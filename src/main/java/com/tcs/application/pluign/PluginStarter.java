/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import com.tcs.application.*;

public class PluginStarter implements Subscriber {
    private Plugin plugin;

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
            this.setPlugin(pluginDataObject);
            if(pluginDataObject.hasDependencies()){
//                Application.getPluginManager().isPluginLoaded();
            }
            String className = pluginDataObject.getClassName();
            String method = pluginDataObject.getActivatorMethod();
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class<?> loadedClass = classLoader.loadClass(className);
            Object obj = loadedClass.newInstance();
            MethodInvoker invoker = new MethodInvoker();
            invoker.invokeTheMethod(obj, method);
            System.out.println("[INFO] - Plugin start SUCCESS for plugin:"+pluginDataObject.getName());
        } catch (Exception e) {
            e.printStackTrace();
            Application.getSubscriptionManager().notifySubscriber(PluginManager.PLUGIN_START_FAIL, this, pluginDataObject);
        }
    }

    public void stopPlugin(PluginDataObject pluignObject) {
        try {
            this.setPlugin(pluignObject);
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

    public Plugin getPlugin() {
        return plugin;
    }

    private void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

}
