/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public interface SubscriptionEventDelegator {
    public void onEvent(SubscriptionEvent event,Object caller);  
}
