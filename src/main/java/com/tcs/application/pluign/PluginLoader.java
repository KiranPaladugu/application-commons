/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.File;
import java.util.Arrays;

import com.tcs.application.*;

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
     
    @Override
    public synchronized void onSubscriptionEvent(SubscriptionEvent event) {
        switch (event.getEvent()) {
        case Application.LOAD_PLUGINS:
            startLoadingPluigns();
            break;
        case Application.START_PLUGIN :
            PluginStarter starter = new PluginStarter();
            try {
                Application.getPluginLoaderQueue().insert(starter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{
            starter.startPluin((PluginDataObject) event.getData());
            }catch (Exception e) {
                Application.getSubscriptionManager().notifySubscriber(Application.PLUGIN_LOAD_FAILED, this, event.getData());;
            }
            break;
        default:
            break;
        }
    }
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
                    System.out.println("Found PluginDataObject with name:"+readFile.getAbsolutePath());
                    Application.getSubscriptionManager().notifySubscriber(Application.PLUGIN_FOUND, this, pluginDataObject);
                }
                
            }

        }else{
            file.mkdirs();
            System.out.println("PluginDataObject folder doesn't exist, now created");
        }
    }


    /* (non-Javadoc)
     * @see com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.SubscriptionEvent)
     */
    @Override
    public void onSubscriptionEvent(SubscriptionEvent event) throws Exception {
        
    }

}
