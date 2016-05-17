/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.tcs.application.pluign.PluginDataObject;
import com.tcs.application.pluign.PluginDependencies;

public class PluginDependenciesTagHandler extends DefaultTaghandler {

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     * jdk.internal.org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        getStack().push(new PluginDependencies());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        PluginDependencies depenDencies = (PluginDependencies) getStack().pop();
        Object obj = getStack().peek();
        if (obj instanceof PluginDataObject) {
            ((PluginDataObject) obj).setDependencies(depenDencies);
        }
    }
}
