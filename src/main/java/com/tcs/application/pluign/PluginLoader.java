/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.pluign;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tcs.application.Application;
import com.tcs.application.JarLoader;
import com.tcs.application.Subscriber;
import com.tcs.application.SubscriptionEvent;
import com.tcs.application.resolver.ResourceResolver;

public class PluginLoader implements Subscriber {

	private final String pluginPath = Application.getApplicaitonRootPath() + File.separator + "plugin";

	/**
	 * 
	 */
	public PluginLoader() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.SubscriptionEvent)
	 * 
	 * @Override public synchronized void onSubscriptionEvent(SubscriptionEvent event) { switch (event.getEvent()) { case
	 * Application.LOAD_PLUGINS: startLoadingPluigns(); break; case Application.START_PLUGIN : PluginStarter starter = new
	 * PluginStarter(); try { Application.getPluginLoaderQueue().insert(starter); } catch (InterruptedException e) {
	 * e.printStackTrace(); } try{ starter.startPluin((PluginDataObject) event.getData()); }catch (Exception e) {
	 * Application.getSubscriptionManager().notifySubscriber(Application.PLUGIN_LOAD_FAILED, this, event.getData());; } break;
	 * default: break; } }
	 */
	protected synchronized void startLoadingPluigns() {
		final File file = new File(pluginPath);
		final JarLoader loader = new JarLoader();
		if (file.exists() && file.isDirectory() && file.canRead()) {
			final File[] files = file.listFiles();
			Arrays.sort(files);
			for (final File readFile : files) {
				final PluginIdentifier identifier = new PluginIdentifier();
				final PluginDataObject pluginDataObject = identifier.identify(readFile);
				if (pluginDataObject != null) {
					loader.loadJar(readFile);
					loader.getPluginDetails();
					System.out.println("[INFO] => Found Plugin from jar name:" + readFile.getAbsolutePath());
					Application.getSubscriptionManager().notifySubscriber(Application.PLUGIN_FOUND, this, pluginDataObject);
					final List<Map<String, String>> resourcesList = getResourcesList(pluginDataObject.getResources());
					Application.getSubscriptionManager().notifySubscriber(ResourceResolver.LOAD_RESOURCES_FROM_PLUGIN_JAR, resourcesList,
							readFile.getAbsolutePath());
				}

			}

		} else {
			file.mkdirs();
			System.out.println("[INFO] => PluginDataObject folder doesn't exist, now created");
		}
	}

	/**
	 * @param resources
	 * @return
	 */
	private List<Map<String, String>> getResourcesList(final List<PluginResource> resources) {
		final List<Map<String, String>> list = new ArrayList<>();
		if (resources.size() > 0) {
			final Map<String, String> map = new HashMap<>();
			for (final PluginResource resource : resources) {
				map.put(resource.getResourceName(), resource.getLocalName());
			}
			list.add(map);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.SubscriptionEvent)
	 */
	@Override
	public void onSubscriptionEvent(final SubscriptionEvent event) throws Exception {

	}

}
