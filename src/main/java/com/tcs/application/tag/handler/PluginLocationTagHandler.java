/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.tcs.application.pluign.PluginDependency;

public class PluginLocationTagHandler extends DefaultTaghandler {
    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     * jdk.internal.org.xml.sax.Attributes)
     */

    private PluginDependency dependency;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (getStack().peek() instanceof PluginDependency) {
            this.dependency = (PluginDependency) getStack().peek();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (dependency.getPluginLocation() != null) {
            dependency.setPluginLocation(dependency.getPluginLocation() + String.valueOf(ch, start, length));
        } else {
            dependency.setPluginLocation(String.valueOf(ch, start, length));
        }
    }
}