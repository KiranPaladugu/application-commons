/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.tcs.application.pluign.PluginDeclaredException;
import com.tcs.application.pluign.PluginDeclaredExceptions;

public class PluginDeclaredExceptionTagHandler extends DefaultTaghandler {

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     * jdk.internal.org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        getStack().push(new PluginDeclaredException(null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        PluginDeclaredException exception = (PluginDeclaredException) getStack().peek();
        if (exception.getExceptionClass() != null) {
            exception.setExceptionClass(exception.getExceptionClass() + String.valueOf(ch, start, length));
        } else {
            exception.setExceptionClass(String.valueOf(ch, start, length));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        PluginDeclaredException exception = (PluginDeclaredException) getStack().pop();
        Object obj = getStack().peek();
        if (obj instanceof PluginDeclaredExceptions) {
            ((PluginDeclaredExceptions) obj).addExcepitonDeclared(exception);
            ;
        }
    }
}
