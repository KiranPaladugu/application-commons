/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public interface Subscriber {
    public void onSubscriptionEvent(SubscriptionEvent event) throws Exception;
}
