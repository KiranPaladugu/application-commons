/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.tcs.application.tag.handler.DefaultTaghandler;

@Deprecated
public class PluginXmlhandler extends DefaultHandler {

	private DefaultTaghandler handler;
	private final Stack<Object> stack = new Stack<>();
	private final Stack<DefaultTaghandler> handlerStack = new Stack<>();;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
	 * org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
		handler = TagHandlerFactory.getFactory().getTagHadler(qName);
		if (handler != null) {
			handlerStack.push(handler);
			handler.setStack(stack);
			handler.startElement(uri, localName, qName, attributes);
		} else {
			handler = null;
			throw new SAXException("[ERROR] => " + "No Hanlder found for tag:" + qName);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException {
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
	public void characters(final char[] ch, final int start, final int length) throws SAXException {
		handler = handlerStack.peek();
		if (handler != null) {
			handler.characters(ch, start, length);
		}
	}

	public Object getDerivedObject() {
		return handler.getDerivedObject();
	}
}
