/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.io.File;

public class Configuration {
	private static final Configuration configuration = new Configuration();
	private File configFile;

	public static final Configuration getApplicationConfiguration() {
		return configuration;
	}

	public Configuration createNewConfiguration() {
		return new Configuration();
	}

	public Configuration createNewConfiguration(File configFile) {
		Configuration config = new Configuration();
		this.configFile = configFile;
		config.readConfig();
		return config;
	}

	private boolean readConfig() {
		if (configFile != null && configFile.exists() && configFile.canRead() && configFile.isFile()) {

		}
		return false;
	}
}
