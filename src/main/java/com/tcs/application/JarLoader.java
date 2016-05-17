/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import com.tcs.application.pluign.PluginDataObject;

public class JarLoader implements Subscriber {

    /**
     * 
     */
    private static final Class<?>[] parameters = new Class[] { URL.class };
    private final String ADD_URL = "addURL";
    

    public JarLoader() {
        Application.getSubscriptionManager().subscribe(this, Application.LOAD_JAR);
    }

    public void loadJar(String absolutePath){
        File file = new File(absolutePath);
        try {
            loadFileWithClassLoader(file);
            Application.getSubscriptionManager().notifySubscriber(Application.JAR_LOAD_SUCCESS,null,absolutePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file
     * @throws Exception
     */
    private void loadFileWithClassLoader(File file) throws Exception {
        if (file.exists() && file.canRead()) {
            try {
                URL urlToLoad = file.toURI().toURL();
                URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                Class<?> sysclass = URLClassLoader.class;
                Method method = sysclass.getDeclaredMethod(ADD_URL, parameters);
                method.setAccessible(true);
                method.invoke(sysloader, new Object[] { urlToLoad });
            } catch (Throwable t) {
                t.printStackTrace();
                throw new Exception("Error, could not add URL to system classloader");
            }
        }
    }

    @Override
    public void onSubscriptionEvent(SubscriptionEvent event) {
        Object eventData = event.getData();
        switch (event.getEvent()) {
        case Application.LOAD_JAR:
            if(eventData!=null ){
                if(eventData instanceof String){
                    loadJar((String) eventData);
                }else if(eventData instanceof File){
                    loadJar((File) eventData);
                }
            }
            break;

        default:
            break;
        }

    }

    /**
     * @param fileToLoad
     */
    public void loadJar(File fileToLoad) {
        try {
            loadFileWithClassLoader(fileToLoad);
            Application.getSubscriptionManager().notifySubscriber(Application.JAR_LOAD_SUCCESS,null,fileToLoad.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            Application.getSubscriptionManager().notifySubscriber(Application.JAR_LOAD_FAIL,null,fileToLoad.getAbsolutePath());
        }
        
    }

    /**
     * @return
     */
    public PluginDataObject getPluginDetails() {
        return null;
    }

}
