/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import com.tcs.application.*;

public class PluginContainer implements Subscriber {
    private static final PluginContainer PLUGIN_CONTAINER = new PluginContainer();
    
    
    public static PluginContainer gePluginContainer(){
        return PLUGIN_CONTAINER;
    }
    
    public  void load(){
        Application.getSubscriptionManager().subscribe(this, Application.PLUGIN_FOUND,Application.PLUGIN_LOAD_COMPLETE);
    }
    
    /**
     * 
     */
    public PluginContainer() {
    }
    
    public synchronized void loadPluign(String Pluginxml){
        
    }

    /* (non-Javadoc)
     * @see com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.SubscriptionEvent)
     */
    @Override
    public synchronized void onSubscriptionEvent(SubscriptionEvent event) {
        switch (event.getEvent()) {
        case Application.PLUGIN_FOUND:
            System.out.println("[INFO] => "+"Found the valid plugin here:"+event.getData());
            Application.getSubscriptionManager().notifySubscriber(PluginManager.START_PLUGIN_REQUEST, this, event.getData());
            break;
        case Application.PLUGIN_LOAD_COMPLETE:

        default:
            break;
        }
    }
}
