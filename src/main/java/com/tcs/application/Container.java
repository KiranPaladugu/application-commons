/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private static final Map<String, Object> containerMap = new HashMap<String, Object>();
    private static Container container;

    public static synchronized void put(String key, Object object) {
        containerMap.put(key, object);
    }

    public static synchronized Object get(String key) {
        return containerMap.get(key);
    }

    public static final Container getApplicationContainer() {
        if (container == null) {
            container = new Container();
        }
        return container;
    }

}
