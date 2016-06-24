/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.tcs.application.tag.handler.DefaultTaghandler;

public class PluginXmlhandler extends DefaultHandler {

    private DefaultTaghandler handler;
    private Stack<Object> stack = new Stack<>();
    private Stack<DefaultTaghandler> handlerStack = new Stack<>();;

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     * org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        handler = TagHandlerFactory.getFactory().getTagHadler(qName);
        if (handler != null) {
            handlerStack.push(handler);
            handler.setStack(stack);
            handler.startElement(uri, localName, qName, attributes);
        } else {
            handler = null;
            throw new SAXException("[WARNING] => "+"No Hanlder found for tag:"+qName);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        handler = handlerStack.pop();
        if (handler != null) {
            handler.setStack(stack);
            handler.endElement(uri, localName, qName);
            
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        handler = handlerStack.peek();
        if (handler != null) {
            handler.characters(ch, start, length);
        }
    }
    
    public Object getDerivedObject(){
        return handler.getDerivedObject();
    }
}
