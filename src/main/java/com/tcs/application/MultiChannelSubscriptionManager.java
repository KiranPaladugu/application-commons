/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.util.*;

public class MultiChannelSubscriptionManager {
    private static final MultiChannelSubscriptionManager channelManager = new MultiChannelSubscriptionManager();
    private Map<String, SubscriptionChannel> channels = new HashMap<String, SubscriptionChannel>();
    
    protected MultiChannelSubscriptionManager(){
        
    }
    public static MultiChannelSubscriptionManager getChannelmanager() {
        return channelManager;
    }
    
    public SubscriptionChannel getSubscriptionChannel(String id,boolean create){
        SubscriptionChannel channel = channels.get(id);
        if(channel == null && create){
            channel = new SubscriptionChannel(id, id);
            channels.put(id, channel);
        }
        return channel;
    }
    
    public SubscriptionChannel getSubscriptionChannel(String id){
        return channels.get(id);
    }
    
    public SubscriptionChannel getSubscriptionChannel(){
        UUID uid = UUID.randomUUID();
        SubscriptionChannel channel = new SubscriptionChannel(uid.toString(), uid.toString());
        channels.put(uid.toString(), channel);
        return channel;
    }
    
    
    
}
