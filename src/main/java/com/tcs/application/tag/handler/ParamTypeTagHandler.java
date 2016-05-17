/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import org.xml.sax.SAXException;

import com.tcs.application.pluign.PluginMethodParam;

public class ParamTypeTagHandler extends DefaultTaghandler {

    private String paramType = "";

    /*
     * (non-Javadoc)
     * 
     * @see com.tcs.application.tag.handler.DefaultTaghandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        paramType = paramType + String.valueOf(ch, start, length);
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
//            ((PluginMethodParam) obj).setType(ParamTypes.valueOf(paramType));
        }
    }

}
