/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public class SubscriptionEvent {
    private Object source;
    private String event;
    private Object data;
    private StackTraceElement caller;

    
    /**
     * @param source
     * @param event
     */
    public SubscriptionEvent(Object source, String event,Object data) {
        super();
        this.source = source;
        this.event = event;
        this.setData(data);
    }

    /**
     * @param source2
     * @param event2
     * @param data2
     * @param caller
     */
    public SubscriptionEvent(Object source, String event, Object data, StackTraceElement caller) {
        this(source, event, data);
        this.setCaller(caller);
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((event == null) ? 0 : event.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
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
        SubscriptionEvent other = (SubscriptionEvent) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (event == null) {
            if (other.event != null)
                return false;
        } else if (!event.equals(other.event))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        return true;
    }

    public StackTraceElement getCaller() {
        return caller;
    }

    public void setCaller(StackTraceElement caller) {
        this.caller = caller;
    }

}
