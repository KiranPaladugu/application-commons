/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import com.tcs.application.pluign.PluginDataObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ActivatorMethodTagHandler extends DefaultTaghandler {
    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     * jdk.internal.org.xml.sax.Attributes)
     */

    private PluginDataObject pluginDataObject;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (getStack().peek() instanceof PluginDataObject) {
            this.pluginDataObject = (PluginDataObject) getStack().peek();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (pluginDataObject.getActivatorMethodName() != null) {
            pluginDataObject.setActivatorMethod(pluginDataObject.getActivatorMethodName() + String.valueOf(ch, start, length));
        } else {
            pluginDataObject.setActivatorMethod(String.copyValueOf(ch, start, length));
        }
    }
}
