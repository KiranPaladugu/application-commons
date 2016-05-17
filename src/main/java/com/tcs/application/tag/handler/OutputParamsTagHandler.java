/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import com.tcs.application.pluign.PluginMethod;
import com.tcs.application.pluign.PluginMethodOutputParam;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class OutputParamsTagHandler extends DefaultTaghandler{

    private PluginMethodOutputParam outParams = new PluginMethodOutputParam();
    
    /* (non-Javadoc)
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String, jdk.internal.org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       getStack().push(outParams);
    }
    
    /* (non-Javadoc)
     * @see com.tcs.application.tag.handler.DefaultTaghandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        getStack().pop();
        Object obj = getStack().peek();
        if(obj instanceof PluginMethod){
            ((PluginMethod)obj).setOutput(outParams);
        }
    }
}
