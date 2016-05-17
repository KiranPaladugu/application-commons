/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.IOException;
import java.util.Properties;

import com.tcs.application.tag.handler.DefaultTaghandler;
import com.tcs.tools.resources.ResourceLocator;

public class TagHandlerFactory {
    private static final TagHandlerFactory factory = new TagHandlerFactory();
    private Properties properties = new Properties();

    /**
     * 
     */
    public TagHandlerFactory() {
        try {
            properties.load(ResourceLocator.getResourceAsAStream("PluignTagHandler.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static TagHandlerFactory getFactory() {
        return factory;
    }

    public DefaultTaghandler getTagHadler(String nameofTag) {

        String className = properties.getProperty("plugin.tagname." + nameofTag);
        if (className != null) {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            try {
                return (DefaultTaghandler) loader.loadClass(className).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
