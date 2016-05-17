/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.util.List;

public interface SubscriptionEventHandler {
    public void onNonScribedEvent(SubscriptionEvent event);
    public void onNotification(String event);
    public void beforeSubscriptionEvent(String event,List<Subscriber> subscibers);
    public void afterSubscriptionEvent(String event,List<Subscriber> subscribers);

}
