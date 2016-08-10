/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.IOException;
import java.util.Properties;

import com.tcs.application.tag.handler.DefaultTaghandler;
import com.tcs.tools.resources.ResourceLocator;

@Deprecated
public class TagHandlerFactory {
	private static final TagHandlerFactory factory = new TagHandlerFactory();
	private Properties properties = new Properties();

	public TagHandlerFactory() {
		try {
			properties.load(ResourceLocator.getResourceAsAStream("PluignTagHandler.properties"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static TagHandlerFactory getFactory() {
		return factory;
	}

	public DefaultTaghandler getTagHadler(final String nameofTag) {

		final String className = properties.getProperty("plugin.tagname." + nameofTag);
		if (className != null) {
			final ClassLoader loader = ClassLoader.getSystemClassLoader();
			try {
				return (DefaultTaghandler) loader.loadClass(className).newInstance();
			} catch (final InstantiationException e) {
				e.printStackTrace();
			} catch (final IllegalAccessException e) {
				e.printStackTrace();
			} catch (final ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(final Properties properties) {
		this.properties = properties;
	}
}
