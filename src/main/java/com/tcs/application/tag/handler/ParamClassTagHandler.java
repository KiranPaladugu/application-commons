/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import org.xml.sax.SAXException;

import com.tcs.application.pluign.PluginMethodParam;

public class ParamClassTagHandler extends DefaultTaghandler {

    private String className = "";

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        className = className + String.valueOf(ch, start, length);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Object obj = getStack().peek();
        if (obj instanceof PluginMethodParam) {
            ((PluginMethodParam) obj).setClassName(className);
        }
    }

}
