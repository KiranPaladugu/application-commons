/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginIdentifier {

    private final String[] fileTypes = { ".jar", ".war", ".ear" };
    private final String pluginFileName = "Plugin.xml";
    private PluginDataObject pluginDataObject;

    public PluginDataObject identify(String fullPathOfFile) {
        File file = new File(fullPathOfFile);
        return identify(file);
        
    }

    /**
     * @param file
     * @return 
     */
    public PluginDataObject identify(File file) {
        if (isSupportedFileType(file.getAbsolutePath())) {
            try {
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> itr = jarFile.entries();
                while (itr.hasMoreElements()) {
                    JarEntry entry = itr.nextElement();
                    if (entry.getName().toLowerCase().endsWith(pluginFileName.toLowerCase())) {
                        String xml = readPluginFile(entry, jarFile);
                        PlguinXmlParser parser = new PlguinXmlParser();
                        parser.parse(xml);
                        pluginDataObject = parser.getDerivedPluginObject();
                        return pluginDataObject;
                    }
                }
                jarFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param entry
     * @return
     * @throws IOException
     * 
     */
    private String readPluginFile(JarEntry entry, JarFile file) throws IOException {
        InputStream inputStream = file.getInputStream(entry);
        byte[] bytes = new byte[16 * 1024];
        int read = -1;
        CharBuffer charBuffer = CharBuffer.allocate(1);
        StringBuffer buffer = new StringBuffer();
        while ((read = inputStream.read(bytes)) != -1) {
            charBuffer = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(bytes, 0, read));
            buffer.append(charBuffer.array());
        }
        inputStream.close();
        return buffer.toString();
    }

    private boolean isSupportedFileType(String fileName) {
        for (String supportedType : fileTypes) {
            if (fileName.toLowerCase().endsWith(supportedType.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
