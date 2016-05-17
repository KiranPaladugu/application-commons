/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.tcs.application.pluign.PluginDeclaredExceptions;
import com.tcs.application.pluign.PluginMethod;

public class PluginDeclaredExceptionsTagHandler extends DefaultTaghandler {

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     * jdk.internal.org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        getStack().push(new PluginDeclaredExceptions());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        PluginDeclaredExceptions exceptions = (PluginDeclaredExceptions) getStack().pop();
        Object obj = getStack().peek();
        if (obj instanceof PluginMethod) {
            ((PluginMethod) obj).setExceptions(exceptions);
        }
    }
}
