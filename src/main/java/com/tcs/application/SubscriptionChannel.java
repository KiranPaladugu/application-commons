/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.util.*;
import java.util.Map.Entry;

public class SubscriptionChannel {

    private Map<String, Set<Subscriber>> subscriptions = new HashMap<String, Set<Subscriber>>();
    private String name, id;
    private int notificationsCount, sendEventCount, subscriptionsCount, removedSubscriptionsCount;
    private Set<SubscriptionEventHandler> eventHandlers = new HashSet<SubscriptionEventHandler>();

    protected SubscriptionChannel() {

    }

    public boolean addSubscriptionEventHandler(SubscriptionEventHandler handler) {
        return eventHandlers.add(handler);
    }

    public boolean removeSubscriptionEventHandler(SubscriptionEventHandler handler) {
        return eventHandlers.remove(handler);
    }

    protected SubscriptionChannel(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public synchronized boolean subscribe(String event, Subscriber subscriber) {
        System.out.println("[INFO] => "+"Subscribe [" + subscriber.getClass().getName() + "] to event [" + event+"]");
        if (subscriptions.containsKey(event)) {
            subscriptionsCount++;
            return subscriptions.get(event).add(subscriber);
        } else {
            Set<Subscriber> list = new HashSet<Subscriber>();
            subscriptions.put(event, list);
            subscriptionsCount++;
            return list.add(subscriber);
        }
    }

    public synchronized void subscribe(Subscriber subscriber, String... events) {
        if (events != null && events.length > 0) {
            for (String event : events) {
                subscribe(event, subscriber);
            }
        }
    }

    public synchronized boolean removeSubscription(String event, Subscriber subscriber) {
        if (subscriptions.containsKey(event)) {
            removedSubscriptionsCount++;
            return subscriptions.get(event).remove(subscriber);
        }
        return false;
    }

    public synchronized boolean removeAllSubscriptions(Subscriber subscriber) {
        boolean flag = false;
        Set<Entry<String, Set<Subscriber>>> entries = subscriptions.entrySet();
        Iterator<Entry<String, Set<Subscriber>>> itr = entries.iterator();
        while (itr.hasNext()) {
            Entry<String, Set<Subscriber>> entry = itr.next();
            boolean val = entry.getValue().remove(subscriber);
            if (val) {
                removedSubscriptionsCount++;
                flag = val;
            }
        }
        return flag;
    }

    public synchronized List<String> getSubscriptions(Subscriber subscriber) {
        List<String> subs = new Vector<String>();
        Set<Entry<String, Set<Subscriber>>> entries = subscriptions.entrySet();
        Iterator<Entry<String, Set<Subscriber>>> itr = entries.iterator();
        while (itr.hasNext()) {
            Entry<String, Set<Subscriber>> entry = itr.next();
            if (entry.getValue().contains(subscriber)) {
                subs.add(entry.getKey());
            }
        }
        return subs;
    }

    public synchronized void notifySubscriber(String event, Object source, Object data) {
        synchronized (subscriptions) {
            notificationsCount++;
            StackTraceElement caller = findCaller();
            System.out
                    .println("[INFO] => "+"Notification event [" + event + "] send by [" + caller.getClassName() + "." + caller.getMethodName()+"]");
            if (subscriptions.containsKey(event)) {
                Set<Subscriber> subscribers = subscriptions.get(event);
                for (Subscriber subscriber : subscribers) {
                    System.out.println("[INFO] => "+"Sending event [" + event + "] to subscriber [" + subscriber.getClass().getName()+"]");
                    sendEventCount++;
                    new ForkedNotifier(subscriber, event, source, data, caller);
                }
            }
        }
    }

    private StackTraceElement findCaller() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        final String currentClassName = this.getClass().getName();
        final String threadClassName = Thread.class.getName();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            final String name = stackTraceElement.getClassName();
            if (currentClassName.equals(name) || threadClassName.equals(name)) {
                continue;
            }
            return stackTraceElement;
        }
        return null;
    }

    public synchronized boolean unSubscribe(Subscriber subscriber) {
        return removeAllSubscriptions(subscriber);
    }

    public synchronized Map<String, Set<Subscriber>> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Map<String, Set<Subscriber>> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public synchronized String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public synchronized void notifySubscriber(String event, Object source) {
        notifySubscriber(event, source, null);
    }

    public synchronized void notifySubscriber(String event) {
        notifySubscriber(event, null, null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((subscriptions == null) ? 0 : subscriptions.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubscriptionChannel other = (SubscriptionChannel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (subscriptions == null) {
            if (other.subscriptions != null)
                return false;
        } else if (!subscriptions.equals(other.subscriptions))
            return false;
        return true;
    }

    public int getSendEventCount() {
        return sendEventCount;
    }

    public void setSendEventCount(int sendEventCount) {
        this.sendEventCount = sendEventCount;
    }

    public int getNotificationsCount() {
        return notificationsCount;
    }

    public void setNotificationsCount(int notificationsCount) {
        this.notificationsCount = notificationsCount;
    }

    public int getSubscriptionsCount() {
        return subscriptionsCount;
    }

    public void setSubscriptionsCount(int subscriptionsCount) {
        this.subscriptionsCount = subscriptionsCount;
    }

    public int getRemovedSubscriptionsCount() {
        return removedSubscriptionsCount;
    }

    public void setRemovedSubscriptionsCount(int removedSubscriptionsCount) {
        this.removedSubscriptionsCount = removedSubscriptionsCount;
    }

    public void printStats() {
        System.out.println("[INFO] => "+"subscribers served:" + this.subscriptionsCount);
        System.out.println("[INFO] => "+"Notifications recieved:" + this.notificationsCount);
        System.out.println("[INFO] => "+"Events send:" + this.sendEventCount);
        System.out.println("[INFO] => "+"Subscriptions removed:" + this.removedSubscriptionsCount);
    }
}
