package com.tcs.application.conf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.tcs.application.Application;

public class ConfigurationManager {
	private static final ConfigurationManager confMan = new ConfigurationManager();

	private final String foldername = "conf";
	private File confFile;

	public void init() {
		Application.getApplicaitonRootPath();
		final Path rootPath = Paths.get(Application.getApplicaitonRootPath());
		if (!rootPath.toFile().exists()) {
			rootPath.toFile().mkdirs();
		}
		confFile = rootPath.resolve(foldername).toFile();
		if (!confFile.exists()) {
			confFile.mkdirs();
		}
	}

	private ConfigurationManager() {
		init();
		loadConfigurations();
	}

	private void loadConfigurations() {
	}

	public FileInputStream getConfigurationAsStream(final String name) throws FileNotFoundException {
		if (confFile != null && name != null) {
			final File fileToRead = Paths.get(confFile.getAbsolutePath()).resolve(name).toFile();
			if (fileToRead.exists() && fileToRead.canRead() && fileToRead.isFile()) {
				final FileInputStream inputStream = new FileInputStream(fileToRead);
				return inputStream;
			}
		}
		return null;
	}

	public String getConfigurationAsString(final String name) throws IOException {
		if (confFile != null && name != null) {
			final File fileToRead = Paths.get(confFile.getAbsolutePath()).resolve(name).toFile();
			if (fileToRead.exists() && fileToRead.canRead() && fileToRead.isFile()) {
				final BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
				final StringBuffer buffer = new StringBuffer();
				String _tmpstr = null;
				while ((_tmpstr = reader.readLine()) != null) {
					buffer.append(_tmpstr + "\n");
				}
				reader.close();
				return buffer.toString();
			}
		}
		return null;
	}

	public List<Object> getConfigurationAsObject(final String name) throws IOException, ClassNotFoundException {
		if (confFile != null && name != null) {
			final File fileToRead = Paths.get(confFile.getAbsolutePath()).resolve(name).toFile();
			if (fileToRead.exists() && fileToRead.canRead() && fileToRead.isFile()) {
				final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToRead));
				final List<Object> list = new LinkedList<>();
				Object tmp = null;
				while ((tmp = ois.readObject()) != null) {
					list.add(tmp);
				}
				ois.close();
				return list;
			}
		}
		return null;
	}

	public File writeStringConfiguration(final String name, final String data) throws IOException {
		return writeStringConfiguration(name, data, false);
	}

	public File writeStringConfiguration(final String name, final String data, final boolean append) throws IOException {
		if (confFile != null && name != null && data != null) {
			final File fileToWrite = Paths.get(confFile.getAbsolutePath()).resolve(name).toFile();
			final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite, append));
			writer.write(data);
			writer.flush();
			writer.close();
			return fileToWrite;
		}
		return null;
	}

	public File writeConfigurationAsObject(final String name, final Object data) throws FileNotFoundException, IOException {
		return writeConfigurationAsObject(name, data, false);
	}

	public File writeConfigurationAsObject(final String name, final Object data, final boolean append) throws FileNotFoundException, IOException {
		if (confFile != null && name != null && data != null) {
			final File fileToWrite = Paths.get(confFile.getAbsolutePath()).resolve(name).toFile();
			final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToWrite, append));
			oos.writeObject(data);
			oos.flush();
			oos.close();
			return fileToWrite;
		}
		return null;
	}

	public static ConfigurationManager getConfigurationManager() {
		return confMan;
	}

	public static List<String> linesAsList(final String data) {
		if (data != null) {
			final String[] lines = data.split("\\\n");
			return Arrays.asList(lines);
		}
		return null;
	}

	public static void main(final String args[]) {
		final String dt = "1\n2\n3\n4\n5\n6\n7\n8\n9\n";
		System.out.println(linesAsList(dt));
	}
}
