/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.tag.handler;

import com.tcs.application.pluign.PluginMethod;
import com.tcs.application.pluign.PluginMethods;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class MethodTagHandler extends DefaultTaghandler{
    private PluginMethod method = null;
    
    /* (non-Javadoc)
     * @see com.tcs.application.tag.handler.DefaultTaghandler#getDerivedObject()
     */
    @Override
    public Object getDerivedObject() {
        return method;
    }
    
    /* (non-Javadoc)
     * @see com.tcs.application.tag.handler.DefaultTaghandler#startElement(java.lang.String, java.lang.String, java.lang.String, jdk.internal.org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String name = null;
        String className = null;
        for(int i=0;i<attributes.getLength();i++){
            if(attributes.getQName(i).equals("name")){
                name = attributes.getValue(i);
            }else if(attributes.getQName(i).equals("class-name")){
                className= attributes.getValue(i);
            }
        }
        method = new PluginMethod(className, name);
        getStack().push(method);
    }
    
    /* (non-Javadoc)
     * @see com.tcs.application.tag.handler.DefaultTaghandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        getStack().pop();
        Object obj = getStack().peek();
        if(obj instanceof PluginMethods){
            ((PluginMethods)obj).addMethod(method);
        }
    }
    
    
}
