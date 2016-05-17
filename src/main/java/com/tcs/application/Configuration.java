/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public class Configuration {
    private static final Configuration configuration = new Configuration();
    
    public static final Configuration getApplicationConfiguration(){
        return configuration;
    }
}
