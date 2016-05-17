/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import com.tcs.application.*;

public class PluginManager implements Subscriber {

    private static final PluginManager PLUGIN_MANAGER = new PluginManager();
    private static BlockingQueue<Object> pluginLoaderQueue = new BlockingQueue<>(1);

    public static final String START_PLUGIN = "startPlugin";
    public static final String START_PLUGIN_REQUEST = "startPlguinRequest";
    public static final String PLUGIN_STARTED = "pluginStarted";
    public static final String START_PLUGIN_REQUEST_SUCESS = "statPlguinRequestSuccess";
    public static final String PLUGIN_START_FAIL = "pluignStartFail";
    public static final String START_PLUGIN_FAIL = "startPlguinRequestFail";
    public static final String LOAD_PLUGIN = "loadPlugin";
    public static final String STOP_PLUGIN = "stopPlugin";
    public static final String STOP_PLUGIN_REQUEST = "stopPluginRequest";
    public static final String PLUGIN_STOP_FAIL = "pluginStopFail";
    public static final String PLUGIN_STOPPED = "pluginStopped";
    public static final String STOP_PLUGIN_REQUEST_SUCESS = "stopPluginRequestSuccess";
    public static final String STOP_PLUGIN_REQUEST_FAIL = "stopPluginRequestFail";
    public static final String PLUGIN_FOUND = "plugin_found";
    public static final String LOAD_PLUGINS = "loadPlugins";
    public static final String PLUGIN_LOAD_FAILED = "pluginLoadFailed";

    private PluginLoader pluignLoader = new PluginLoader();

    /**
     * 
     */
    public PluginManager() {
        getPluginContainer().load();
    }

    public PluginContainer getPluginContainer() {
        return PluginContainer.gePluginContainer();
    }

    public void start() {
        Application.getSubscriptionManager().subscribe(this, PLUGIN_STARTED, PLUGIN_STOPPED, PLUGIN_START_FAIL, PLUGIN_STOP_FAIL,
                START_PLUGIN_REQUEST, STOP_PLUGIN_REQUEST, LOAD_PLUGINS, PLUGIN_FOUND);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.SubscriptionEvent)
     */
    @Override
    public void onSubscriptionEvent(SubscriptionEvent event) throws Exception {
        switch (event.getEvent()) {
        case Application.LOAD_PLUGINS:
            this.pluignLoader.startLoadingPluigns();
            break;
        case START_PLUGIN_REQUEST :
            PluginStarter starter = new PluginStarter();
            try {
                pluginLoaderQueue.insert(starter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{
                System.out.println("Starting plugin"+((PluginDataObject) event.getData()).getName());
            starter.startPluin((PluginDataObject) event.getData());
            }catch (Exception e) {
                Application.getSubscriptionManager().notifySubscriber(Application.PLUGIN_LOAD_FAILED, this, event.getData());;
            }
            break;
        case PLUGIN_LOAD_FAILED:
            System.out.println("Plugin load failed");
        case PLUGIN_STARTED:
            try {
                Object obj = pluginLoaderQueue.peek();
                if(obj != null && obj instanceof PluginStarter){
                    pluginLoaderQueue.remove(30);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }        
        }
    }

    public static PluginManager getPluginManager() {
        return PLUGIN_MANAGER;
    }

    /**
     * 
     */
    public void stopAllPlugins() {
        
    }

}
