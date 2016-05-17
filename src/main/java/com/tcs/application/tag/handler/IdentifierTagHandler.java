/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.tcs.application.pluign.PluginDataObject;



public class IdentifierTagHandler extends DefaultTaghandler {
    /* (non-Javadoc)
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String, jdk.internal.org.xml.sax.Attributes)
     */
    
    private PluginDataObject pluginDataObject;
    
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(getStack().peek() instanceof PluginDataObject){
            this.pluginDataObject = (PluginDataObject) getStack().peek();
        }
    }
    
    /* (non-Javadoc)
     * @see com.tcs.application.tag.handler.DefaultTaghandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       if(pluginDataObject.getIdentifier()!=null){
           pluginDataObject.setIdentifier(pluginDataObject.getIdentifier()+String.valueOf(ch,start,length));
       }else{
           pluginDataObject.setIdentifier(String.valueOf(ch,start,length));
       }
    }
}
