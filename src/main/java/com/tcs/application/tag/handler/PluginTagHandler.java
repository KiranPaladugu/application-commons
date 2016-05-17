/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.tcs.application.pluign.PluginDataObject;

public class PluginTagHandler extends DefaultTaghandler {

    private PluginDataObject pluginDataObject;

    /**
     * 
     */
    public PluginTagHandler() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#getDerivedObject()
     */
    @Override
    public Object getDerivedObject() {
        return pluginDataObject;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     * jdk.internal.org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        pluginDataObject = new PluginDataObject(null, null);
        if (attributes.getLength() > 0) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getQName(i).equals("name")) {
                    pluginDataObject.setName(attributes.getValue(i));
                } else if (attributes.getQName(i).equals("className")) {
                    pluginDataObject.setClassName(attributes.getValue(i));
                } else if (attributes.getQName(i).equals("version")) {
                    pluginDataObject.setVersion(attributes.getValue(i));
                }
            }
        }
        getStack().push(pluginDataObject);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }
}
