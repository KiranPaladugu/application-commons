/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public class ForkedNotifier extends Thread {
    private Object source;
    private String event;    
    private Subscriber subscriber;
    private Object data;
    private StackTraceElement caller;
   
    /**
     * @param subscriber
     * @param event
     * @param source
     * @param data
     * @param caller
     */
    public ForkedNotifier(Subscriber subscriber, String event, Object source, Object data, StackTraceElement caller) {
        this.caller = caller;
        this.event = event;
        this.source = source;
        this.subscriber = subscriber;
        this.data = data;
        this.start();
    }
    
    public void run() {
        try {
            subscriber.onSubscriptionEvent(new SubscriptionEvent(source, event,data,caller));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public StackTraceElement getCaller() {
        return caller;
    }
    public void setCaller(StackTraceElement caller) {
        this.caller = caller;
    }
}
