/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.tools.resources;

import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ResourceLocator {
    public synchronized static ImageIcon getImageIcon(String name) {
//        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        InputStream stream = getResourceAsAStream(name);
        if (stream != null) {
            try {
                return new ImageIcon(ImageIO.read(stream));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public synchronized static URL getResource(String name) {
        return ResourceLocator.class.getResource(name);
    }

    public synchronized static InputStream getResourceAsAStream(String name) {
        return ResourceLocator.class.getResourceAsStream(name);
    }
    
    public synchronized static File getResourceFile(String name){
        return new File(ResourceLocator.class.getResource(name).getFile());
}
}
