/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import com.tcs.application.conf.ConfigurationManager;
import com.tcs.application.pluign.PluginContainer;
import com.tcs.application.pluign.PluginManager;
import com.tcs.application.resolver.ResourceResolver;
import com.tcs.application.tray.ApplicationTray;

public class Application implements Subscriber {

	public static final String EXIT = "exit";
	public static final String START = "start";
	public static final String STARTED = "started";
	public static final String JAR_LOAD_SUCCESS = "JarLoadSucess";
	public static final String LOAD_JAR = "loadJar";
	public static final String JAR_LOAD_FAIL = "JarLoadFail";
	public static final String LOAD_PLUGINS = "loadPlugins";
	public static final String PLUGIN_FOUND = "plugin_found";
	public static final String PLUGIN_LOAD_FAILED = "pluginLoadFailed";
	public static final String PLUGIN_LOAD_COMPLETE = "pluignLoadingCompleted";
	public static final String LOAD_RESOURCES = "loadResources";
	private static final String Applciation_root = System.getProperty("user.home") + File.separator + ".tcs";

	private static Application application = new Application();
	private final AtomicBoolean started = new AtomicBoolean(false);

	public static File getApplicationRoot() {
		return new File(Applciation_root);
	}

	public static String getApplicaitonRootPath() {
		return Applciation_root;
	}

	private Application() {
		Application.getSubscriptionManager().subscribe(this, START, STARTED, EXIT, PLUGIN_LOAD_FAILED);
		Runtime.getRuntime().addShutdownHook(new ShutdownThreadHook());
	}

	public static final Context getApplicationContext() {
		return Context.getApplicationContext();
	}

	public static final Container getApplicationContainer() {
		return Container.getApplicationContainer();
	}

	public static final Configuration getApplicationConfiguration() {
		return Configuration.getApplicationConfiguration();
	}

	public static final SubscriptionChannel getSubscriptionManager() {
		return SingleChannelSubscriptionManger.getSubscriptionManger();
	}

	public static final PluginContainer getPluginContainer() {
		return PluginContainer.gePluginContainer();
	}

	public static final Application getApplication() {
		return application;
	}

	public static final PluginManager getPluginManager() {
		return PluginManager.getPluginManager();
	}

	public static final ResourceResolver getResourceResolver() {
		return ResourceResolver.getResourceResolver();
	}

	public static final ConfigurationManager getConfigurationManager() {
		return ConfigurationManager.getConfigurationManager();
	}

	public synchronized Application startApplication() {
		/*
		 * if (!started.get()) { MainWindow window = new MainWindow(); StartUi starter = new StartUi(window); try {
		 * SwingUtilities.invokeAndWait(starter); } catch (InvocationTargetException e) { e.printStackTrace(); } catch
		 * (InterruptedException e) { e.printStackTrace(); } }
		 */
		new ApplicationTray();
		getSubscriptionManager().notifySubscriber(START, this);
		return this;
	}

	public synchronized void shutDown() {
		if (started.get()) {

		}
	}

	public static void main(final String[] args) {
		Application.getApplication().startApplication();

	}

	@Override
	public synchronized void onSubscriptionEvent(final SubscriptionEvent event) {
		switch (event.getEvent()) {
		case START:
			getResourceResolver().start();
			getPluginManager().start();
			getSubscriptionManager().notifySubscriber(LOAD_PLUGINS);
			this.started.set(true);
			break;
		case EXIT:
			System.out.println("[INFO] => Exiting Application..");
			getPluginManager().stopAllPlugins();
			Application.getSubscriptionManager().printStats();
			System.exit(0);
		case PLUGIN_LOAD_FAILED:
			System.out.println("[WARNING] => Plugin load failed");
			break;
		default:
			break;
		}

	}

}
