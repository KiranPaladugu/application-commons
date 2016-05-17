/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public class SingleChannelSubscriptionManger {
    private static SubscriptionChannel subscriptionChannel = new SubscriptionChannel();
    public static final SubscriptionChannel getSubscriptionManger(){
        return subscriptionChannel;
    }

}
