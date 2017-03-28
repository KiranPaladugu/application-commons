/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application.resolver;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.tcs.application.*;

public class ResourceResolver extends AbstractResolver implements Subscriber {
	private static ResourceResolver resourceResolver;
	private final String[] fileTypes = { ".jar", ".war", ".ear" };
	private final String[] resourceTypes = { ".xml", ".txt", ".properties" };
	private Map<String, String> resourceMap = new HashMap<String, String>();
	public static final String PROPTERY_SYSTEM_TEMP = "java.io.tmpdir";
	public static final String DIRECTORY_SYTEM_TEMP = System.getProperty(PROPTERY_SYSTEM_TEMP);
	public static final String LOAD_RESOURCES_FROM_PLUGIN_JAR = "load_resources_from_pluign_jar";
	private File tmpDir;
	
	private ResourceResolver() {
		Application.getSubscriptionManager().subscribe(this, Application.LOAD_RESOURCES,
				LOAD_RESOURCES_FROM_PLUGIN_JAR);
	}

	public void start() {
		Application.getSubscriptionManager().notifySubscriber(Application.LOAD_RESOURCES);
	}

	public static ResourceResolver getResourceResolver() {
		if (resourceResolver == null) {
			resourceResolver = new ResourceResolver();
		}
		return resourceResolver;
	}

	public synchronized static String getRandomUniqueId() {
		return UUID.randomUUID().toString();
	}

	public File getApplicationTempDir() {
		if (tmpDir == null) {
			tmpDir = new File(DIRECTORY_SYTEM_TEMP + File.separator + getRandomUniqueId());
			while (tmpDir.exists()) {
				tmpDir = new File(DIRECTORY_SYTEM_TEMP + File.separator + getRandomUniqueId());
			}
			tmpDir.mkdirs();
			tmpDir.deleteOnExit();
		}
		return tmpDir;
	}

	private String pluginPath = System.getProperty("user.home") + File.separator + ".tcs" + File.separator + "plugin";

	public InputStream getResourceAsStream(String resourceName) {
		if (resourceName != null && resourceName.length() > 0) {
			File file = null;
			if (resourceMap.containsKey(resourceName)) {
				file = new File(resourceMap.get(resourceName));
			}
			if (file == null || !file.exists()) {
				file = loadResource(resourceName);
			}
			if (file != null && file.exists() && file.canRead()) {
				try {
					return new FileInputStream(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private File loadResource(String resourceName) {
		File file = new File(pluginPath);
		if (file.exists() && file.isDirectory() && file.canRead()) {
			File[] files = file.listFiles();
			Arrays.sort(files);
			for (File readFile : files) {
				File foundResource = findResource(readFile, resourceName);
				if (foundResource != null)
					return foundResource;

			}
		}
		return null;
	}

	public File extractArchiveEntry(ZipEntry zipEntryToExtract, ZipFile zipFileFromToExtract) {
		File tmpFile = null;
		String extn = getExtension(zipEntryToExtract);

		while (tmpFile == null || tmpFile.exists()) {
			tmpFile = new File(getNewTmpFile(extn));
		}
		try {
			BufferedInputStream zipIn = new BufferedInputStream(zipFileFromToExtract.getInputStream(zipEntryToExtract));
			BufferedOutputStream zipOut = new BufferedOutputStream(new FileOutputStream(tmpFile));
			byte[] array = new byte[2500];
			int lenght = -1;
			while ((lenght = zipIn.read(array)) != -1) {
				zipOut.write(array, 0, lenght);
			}
			zipOut.close();
			zipIn.close();
			System.out.println(String.format("[DEBUG] => file [%s] extracted from [%s] to \n\t > file [%s]",
					zipEntryToExtract.getName(), zipFileFromToExtract.getName(), tmpFile.getAbsolutePath()));
			return tmpFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public File extractArchiveEntry(String fileToExtract, ZipFile zipFile) {
		Enumeration<? extends ZipEntry> entriesItr = zipFile.entries();
		ZipEntry entry = null;
		while (entriesItr.hasMoreElements()) {
			ZipEntry ent = entriesItr.nextElement();
			if (ent.getName().equals(fileToExtract)) {
				entry = ent;
				break;
			}
		}
		if (entry != null) {
			return extractArchiveEntry(entry, zipFile);
		}
		return null;
	}

	private File findResource(File file, String resourceName) {
		if (isSupportedArchiveType(file.getAbsolutePath())) {
			JarFile jarFile = null;
			try {
				jarFile = new JarFile(file);
				Enumeration<JarEntry> itr = jarFile.entries();
				while (itr.hasMoreElements()) {
					JarEntry entry = itr.nextElement();
					if (entry.getName().endsWith(resourceName)) {
						System.out.println(
								"[INFO] => String found Reosurce :" + resourceName + " in :" + file.getAbsolutePath());
						File extracted = this.extractArchiveEntry(entry, jarFile);
						if (extracted != null) {
							this.resourceMap.put(resourceName, extracted.getAbsolutePath());
							jarFile.close();
							return extracted;
						}
					}
				}
				jarFile.close();
			} catch (IOException e) {
				if (jarFile != null)
					try {
						jarFile.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				e.printStackTrace();

				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean isSupportedArchiveType(String fileName) {
		for (String supportedType : fileTypes) {
			if (fileName.toLowerCase().endsWith(supportedType.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param entry
	 * @return
	 */
	private String getExtension(ZipEntry entry) {
		String name = entry.getName();
		String extn = "";
		int offset = name.lastIndexOf('.');
		if (offset != -1) {
			extn = name.substring(offset, name.length());
		}
		if (extn.length() < 1) {
			extn = ".archive";
		}
		return extn;
	}

	private String getNewTmpFile(String extn) {
		UUID uuid = UUID.randomUUID();
		String tmpnFileame = uuid.toString() + extn;
		String tmpFilePath = getApplicationTempDir() + File.separator + tmpnFileame;
		return tmpFilePath;

	}

	@Override
	public synchronized void onSubscriptionEvent(SubscriptionEvent event) throws Exception {
		switch (event.getEvent()) {
		case Application.LOAD_RESOURCES:
			System.out.println("[INFO] => Loading resource start...");
			break;
		case LOAD_RESOURCES_FROM_PLUGIN_JAR:
			String fileName = (String) event.getData();
			if (event.getSource() != null && event.getSource() instanceof List<?>) {
				@SuppressWarnings("unchecked")
				List<Map<String, String>> list = (List<Map<String, String>>) event.getSource();
				if (list.size() > 0) {
					System.out.println("[INFO] => Loading reources from jar:" + fileName);
					findResources(new File(fileName), list);
				}
			}
			break;
		default:
			break;
		}
	}

	public synchronized void findResources(File file, List<Map<String, String>> list) {
		if (file != null && file.exists() && file.canRead()) {
			if (isSupportedArchiveType(file.getAbsolutePath())) {
				JarFile jarFile = null;
				try {
					jarFile = new JarFile(file);
					List<String> resList = new ArrayList<>();
					Map<String, String> newMap = getRequiredResourcesAsList(list, resList);
					Enumeration<JarEntry> itr = jarFile.entries();
					while (itr.hasMoreElements()) {
						JarEntry entry = itr.nextElement();
						String resourceName = entry.getName();
						String value = null;
						if ((value = getPluginRequiredResource(resourceName, resList)) != null) {
							System.out.println(
									"[INFO] => Found Reosurce :" + resourceName + " in :" + file.getAbsolutePath());
							File extracted = this.extractArchiveEntry(entry, jarFile);
							if (extracted != null) {
								this.resourceMap.put(resourceName, extracted.getAbsolutePath());
								this.resourceMap.put(newMap.get(value), extracted.getAbsolutePath());
							}
						}
					}
					jarFile.close();
				} catch (IOException e) {
					if (jarFile != null)
						try {
							jarFile.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param resourceName
	 * @param resList
	 * @return
	 */
	private String getPluginRequiredResource(String resourceName, List<String> resList) {
		if (resList.contains(resourceName)) {
			return resourceName;
		}
		for (String requiredResoure : resList) {
			if (resourceName.endsWith(requiredResoure)) {
				return requiredResoure;
			}
		}
		return null;
	}

	/**
	 * @param resourceName
	 * @param list
	 * @return
	 */
	private Map<String, String> getRequiredResourcesAsList(List<Map<String, String>> list, List<String> asList) {
		Map<String, String> listToReturn = new HashMap<>();
		for (Map<String, String> entryMap : list) {
			if (entryMap != null && entryMap.size() > 0) {
				Set<Entry<String, String>> entrySet = entryMap.entrySet();
				for (Entry<String, String> entry : entrySet) {
					asList.add(entry.getValue());
					listToReturn.put(entry.getValue(), entry.getKey());
				}
			}
		}
		return listToReturn;
	}

	public boolean isSupportedResourceType(String entryName) {
		for (String supportedType : this.resourceTypes) {
			if (entryName.toLowerCase().endsWith(supportedType.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void destroy() {
		if (tmpDir != null && tmpDir.exists()) {
			tmpDir.deleteOnExit();
			deleteFilesCascade(tmpDir);
		}
	}

	private void deleteFilesCascade(File file) {
		if (file != null && file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File tmpFile : files) {
					deleteFilesCascade(tmpFile);
				}
			}
			System.out.println("[DEBUG] - Deleting file :" + file.getAbsolutePath());
			file.delete();
		}
	}

	@Override
	public String getResolverNamespace() {
		return "resource";
	}

	@Override
	public InputStream resolve(String uri) {
		return getResourceAsStream((String) parse(uri));
	}
	
	public String getJarName(Class<?> klass){
	    URL location = klass.getResource('/' + klass.getName().replace('.', '/') + ".class");
	    if(location!=null){
	        return location.getFile();
	    }
	    return "";
	}
}
