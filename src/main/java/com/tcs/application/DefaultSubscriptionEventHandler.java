/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.util.List;

public class DefaultSubscriptionEventHandler implements SubscriptionEventHandler {

    /* (non-Javadoc)
     * @see com.tcs.application.SubscriptionEventHandler#onNonScribedEvent(com.tcs.application.SubscriptionEvent)
     */
    @Override
    public void onNonScribedEvent(SubscriptionEvent event) {
        

    }

    /* (non-Javadoc)
     * @see com.tcs.application.SubscriptionEventHandler#onNotification(java.lang.String)
     */
    @Override
    public void onNotification(String event) {

    }

    /* (non-Javadoc)
     * @see com.tcs.application.SubscriptionEventHandler#beforeSubscriptionEvent(java.lang.String, java.util.List)
     */
    @Override
    public void beforeSubscriptionEvent(String event, List<Subscriber> subscibers) {

    }

    /* (non-Javadoc)
     * @see com.tcs.application.SubscriptionEventHandler#afterSubscriptionEvent(java.lang.String, java.util.List)
     */
    @Override
    public void afterSubscriptionEvent(String event, List<Subscriber> subscribers) {

    }

}
