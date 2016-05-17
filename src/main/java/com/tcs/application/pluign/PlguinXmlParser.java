/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.tcs.tools.resources.ResourceLocator;

public class PlguinXmlParser {
    private PluginDataObject pluginDataObject;
    private SAXParser saxParser;

    /**
     * 
     */
    public PlguinXmlParser() {
        try {
            saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.getXMLReader().setFeature("http://xml.org/sax/features/validation", false);
            saxParser.getXMLReader().setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            saxParser.getXMLReader().setFeature("http://apache.org/xml/features/allow-java-encodings", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(InputStream stream) {
        try {
            if (saxParser != null) {
                PluginXmlhandler handler = new PluginXmlhandler();
                saxParser.parse(stream, handler);
                setPluginObject((PluginDataObject) handler.getDerivedObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(String strXml) {
        parse(new ByteArrayInputStream(strXml.getBytes()));
    }

    public static void main(String[] args) {
        PlguinXmlParser pareser = new PlguinXmlParser();
        pareser.parse(ResourceLocator.getResourceAsAStream("PluginDataObject.xml"));
        System.out.println(pareser.getPluginObject());
    }

    /**
     * @return
     */
    public PluginDataObject getDerivedPluginObject() {
        return pluginDataObject;
    }

    public PluginDataObject getPluginObject() {
        return pluginDataObject;
    }

    public void setPluginObject(PluginDataObject pluginDataObject) {
        this.pluginDataObject = pluginDataObject;
    }
}
