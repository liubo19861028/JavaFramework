package com.framework.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 接口或类的说明:
 *
 * <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2017-11-7 下午8:23:42 <br>
 * ==========================
 *
 */
public class PropertyUtil extends BaseUtil {

	private static Log LOG = LogFactory.getLog(PropertyUtil.class);

	private static Hashtable<String, Properties> pptContainer = new Hashtable();

	private static String pptSuffixName = ".properties";

	private static Map<String, Properties> i18nPptContainer = new Hashtable();

	public static final String getValue(String propertyFilePath, String key) {
		return getValue(null, propertyFilePath, key);
	}

	public static final String getValue(Class<?> cls, String propertyFilePath, String key) {
		Properties ppts = getProperties(cls, propertyFilePath);
		return ppts == null ? null : ppts.getProperty(key);
	}

	public static final String getValue(String propertyFilePath, String key, boolean isAbsolutePath) {
		if (isAbsolutePath) {
			Properties ppts = getPropertiesByFs(propertyFilePath);
			return ppts == null ? null : ppts.getProperty(key);
		}
		return getValue(propertyFilePath, key);
	}

	public static final Properties getProperties(String propertyFilePath) {
		return getProperties(null, propertyFilePath);
	}

	public static final Properties getProperties(Class<?> cls, String propertyFilePath) {
		if (propertyFilePath == null) {
			LOG.error("propertyFilePath is null!");
			return null;
		}
		Properties ppts = (Properties) pptContainer.get(propertyFilePath);
		if (ppts == null) {
			ppts = loadPropertyFile(cls, propertyFilePath);
			if (ppts != null) {
				pptContainer.put(propertyFilePath, ppts);
			}
		}
		return ppts;
	}

	public static final Properties getPropertiesByFs(String propertyFilePath) {
		if (propertyFilePath == null) {
			LOG.error("propertyFilePath is null!");
			return null;
		}
		Properties ppts = (Properties) pptContainer.get(propertyFilePath);
		if (ppts == null) {
			ppts = loadPropertyFileByFileSystem(propertyFilePath);
			if (ppts != null) {
				pptContainer.put(propertyFilePath, ppts);
			}
		}
		return ppts;
	}

	private static Properties loadPropertyFile(Class<?> cl, String propertyFilePath) {
		InputStream is = getClassLoader(cl).getResourceAsStream(propertyFilePath);
		if (is == null) {
			return loadPropertyFileByFileSystem(propertyFilePath);
		}
		Properties ppts = new Properties();
		try {
			ppts.load(is);
			return ppts;
		} catch (Exception e) {
			LOG.debug("加载属性文件出错:" + propertyFilePath, e);
		}
		return null;
	}

	private static Properties loadPropertyFileByFileSystem(String propertyFilePath) {
		try {
			Properties ppts = new Properties();
			ppts.load(new FileInputStream(propertyFilePath));
			return ppts;
		} catch (FileNotFoundException e) {
			LOG.error("FileInputStream(\"" + propertyFilePath + "\")! FileNotFoundException: " + e);
			return null;
		} catch (IOException e) {
			LOG.error("Properties.load(InputStream)! IOException: " + e);
		}
		return null;
	}

	public static final boolean setValueAndStore(String propertyFilePath, Hashtable<String, String> htKeyValue) {
		return setValueAndStore(propertyFilePath, htKeyValue, null);
	}

	public static final boolean setValueAndStore(String propertyFilePath, Hashtable<String, String> htKeyValue,
			String storeMsg) {
		Properties ppts = getProperties(propertyFilePath);
		if ((ppts == null) || (htKeyValue == null)) {
			return false;
		}

		ppts.putAll(htKeyValue);
		OutputStream stream = null;
		try {
			stream = new FileOutputStream(propertyFilePath);
		} catch (FileNotFoundException e) {
			String path = PropertyUtil.class.getResource(propertyFilePath).getPath();
			if (LOG.isDebugEnabled()) {
				LOG.debug("propertyFilePath = " + propertyFilePath);
				LOG.debug("path = " + path);
			}
			try {
				stream = new FileOutputStream(path);
			} catch (FileNotFoundException e1) {
				LOG.error("文件不存在［" + propertyFilePath + "］");
				return false;
			}
		}
		try {
			ppts.store(stream, storeMsg != null ? storeMsg : "set value and store.");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("存储属性文件时出错", e);
		}
		return false;
	}

	public static final boolean createPropertiesFile(String propertyFilePath, Hashtable<String, String> htKeyValue) {
		File file = new File(propertyFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				LOG.error("创建文件失败", e);
			}
		}
		return setValueAndStore(propertyFilePath, htKeyValue, "create properties file:" + file.getName());
	}

	public static final boolean setValue(String propertyFilePath, String key, String value) {
		Properties ppts = getProperties(propertyFilePath);
		if (ppts == null) {
			return false;
		}
		ppts.put(key, value);
		return true;
	}

	public static final void store(Properties properties, String propertyFilePath, String msg) {
		try {
			OutputStream stream = new FileOutputStream(propertyFilePath);
			properties.store(stream, msg);
		} catch (FileNotFoundException e) {
			LOG.error("文件不存在[" + propertyFilePath + "]", e);
		} catch (IOException e) {
			LOG.error("保存属性文件对象失败", e);
		}
	}

	public static final String removeValue(String propertyFilePath, String key) {
		Properties ppts = getProperties(propertyFilePath);
		if (ppts == null) {
			return null;
		}
		return (String) ppts.remove(key);
	}

	public static final Properties removeValue(String propertyFilePath, String[] key) {
		if (key == null) {
			LOG.error("key[] is null!");
			return null;
		}
		Properties ppts = getProperties(propertyFilePath);
		if (ppts == null) {
			return null;
		}
		for (String strKey : key) {
			ppts.remove(strKey);
		}
		return ppts;
	}

	public static final boolean removeValueAndStore(String propertyFilePath, String[] key) {
		Properties ppts = removeValue(propertyFilePath, key);
		if (ppts == null) {
			return false;
		}
		store(ppts, propertyFilePath, "batch remove key value!");
		return true;
	}

	public static final boolean updateValue(String propertyFilePath, String key, String newValue) {
		if ((key == null) || (newValue == null)) {
			LOG.error("key or newValue is null!");
			return false;
		}
		Hashtable ht = new Hashtable();
		ht.put(key, newValue);
		return setValueAndStore(propertyFilePath, ht, "update " + key + "'s value!");
	}

	public static final boolean batchUpdateValue(String propertyFilePath, Hashtable<String, String> htKeyValue) {
		if ((propertyFilePath == null) || (htKeyValue == null)) {
			return false;
		}
		return setValueAndStore(propertyFilePath, htKeyValue, "batch update key value!");
	}

	public static final Properties removePropertyFile(String propertyFilePath) {
		return (Properties) pptContainer.remove(propertyFilePath);
	}

	public static final String getPpropertyFilePath(String pkg, String propertyFileName) {
		pkg = pkg == null ? "" : pkg.replaceAll("\\.", "/");
		propertyFileName = propertyFileName + pptSuffixName;
		return "/" + pkg + "/" + propertyFileName;
	}

	protected static final Properties getI18nLocaleProperties(String jspPath, Locale local) {
		if ((isBlank(jspPath)) || (!jspPath.endsWith(".jsp"))) {
			return new Properties();
		}
		String jpd = jspPath.substring(0, jspPath.lastIndexOf("."));
		String jpb = jpd + pptSuffixName;
		String jpl = "";

		if (local != null)
			jpl = jpd + "_" + local + pptSuffixName;
		else
			jpl = jpb;
		if (LOG.isDebugEnabled()) {
			LOG.debug("jspPath = " + jpl);
		}
		Properties ppt = (Properties) i18nPptContainer.get(jpl);
		if ((ppt != null) && (!ppt.isEmpty()))
			return ppt;
		ppt = getProperties(jpl);
		if ((ppt != null) && (!ppt.isEmpty())) {
			i18nPptContainer.put(jpl, ppt);
			return ppt;
		}

		ppt = (Properties) i18nPptContainer.get(jpb);
		if ((ppt != null) && (!ppt.isEmpty()))
			return ppt;
		ppt = getProperties(jpb);
		if ((ppt != null) && (!ppt.isEmpty())) {
			i18nPptContainer.put(jpb, ppt);
			return ppt;
		}

		if (LOG.isDebugEnabled())
			LOG.warn("Not find the corresponding resource file internationalization!");
		return new Properties();
	}

}
