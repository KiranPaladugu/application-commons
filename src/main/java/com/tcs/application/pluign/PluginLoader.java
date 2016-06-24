/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.File;
import java.util.*;

import com.tcs.application.*;
import com.tcs.application.resolver.ResourceResolver;

public class PluginLoader implements Subscriber {

    private String pluginPath = System.getProperty("user.home") + File.separator + ".tcs" + File.separator + "plugin";

    /**
     * 
     */
    public PluginLoader() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.SubscriptionEvent)
     * 
     * @Override public synchronized void onSubscriptionEvent(SubscriptionEvent event) { switch (event.getEvent()) { case
     * Application.LOAD_PLUGINS: startLoadingPluigns(); break; case Application.START_PLUGIN : PluginStarter starter = new
     * PluginStarter(); try { Application.getPluginLoaderQueue().insert(starter); } catch (InterruptedException e) {
     * e.printStackTrace(); } try{ starter.startPluin((PluginDataObject) event.getData()); }catch (Exception e) {
     * Application.getSubscriptionManager().notifySubscriber(Application.PLUGIN_LOAD_FAILED, this, event.getData());; } break;
     * default: break; } }
     */
    protected synchronized void startLoadingPluigns() {
        File file = new File(pluginPath);
        JarLoader loader = new JarLoader();
        if (file.exists() && file.isDirectory() && file.canRead()) {
            File[] files = file.listFiles();
            Arrays.sort(files);
            for (File readFile : files) {
                PluginIdentifier identifier = new PluginIdentifier();
                PluginDataObject pluginDataObject = identifier.identify(readFile);
                if (pluginDataObject != null) {
                    loader.loadJar(readFile);
                    loader.getPluginDetails();
                    System.out.println("[INFO] => Found Plugin from jar name:" + readFile.getAbsolutePath());
                    Application.getSubscriptionManager().notifySubscriber(Application.PLUGIN_FOUND, this, pluginDataObject);
                    List<Map<String, String>> resourcesList = getResourcesList(pluginDataObject.getResources());
                    Application.getSubscriptionManager().notifySubscriber(ResourceResolver.LOAD_RESOURCES_FROM_PLUGIN_JAR,resourcesList, readFile.getAbsolutePath());
                }

            }

        } else {
            file.mkdirs();
            System.out.println("[INFO] => PluginDataObject folder doesn't exist, now created");
        }
    }

    /**
     * @param resources
     * @return
     */
    private List<Map<String, String>> getResourcesList(List<PluginResource> resources) {
        List<Map<String, String>> list = new ArrayList<>();
        if(resources.size()>0){
           Map<String, String> map = new HashMap<>();
           for(PluginResource resource:resources){
               map.put(resource.getResourceName(), resource.getLocalName());
           }
           list.add(map);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.SubscriptionEvent)
     */
    @Override
    public void onSubscriptionEvent(SubscriptionEvent event) throws Exception {

    }

}
