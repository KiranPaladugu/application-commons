/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static Context context;


    private static final Map<String, Object> configuration = new HashMap<String, Object>();

    public synchronized void put(String key, Object object) {
        configuration.put(key, object);
    }

    public synchronized Object get(String key) {
        return configuration.get(key);
    }

    public static Context getApplicationContext() {
        if (context == null) {
            context = new Context();
        }
        return context;
    }
    
}
