package com.framework.comm.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseUtil {
	public static Class<?> loadClass(String className) {
		try {
			return getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("class not found '" + className + "'", e);
		}
	}

	public static ClassLoader getClassLoader() {
		return getClassLoader(null);
	}

	public static ClassLoader getClassLoader(Class<?> cls) {
		ClassLoader cl = null;
		if (cls != null)
			cl = cls.getClassLoader();
		if (cl == null)
			cl = Thread.currentThread().getContextClassLoader();
		if (cl == null)
			cl = BaseUtil.class.getClassLoader();
		if (cl == null)
			throw new RuntimeException("Cannot determine classloader");
		return cl;
	}

	public static final boolean isBlank(Object obj) {
		return obj == null;
	}

	public static final boolean isBlank(String str) {
		return (str == null) || (str.trim().length() <= 0);
	}

	public static final boolean isBlank(Character cha) {
		return (cha == null) || (cha.equals(Character.valueOf(' ')));
	}

	public static final boolean isBlank(Serializable obj) {
		return obj == null;
	}

	public static final boolean isBlank(Object[] objs) {
		return (objs == null) || (objs.length <= 0);
	}

	public static final boolean isBlank(Collection<Object> obj) {
		return (obj == null) || (obj.isEmpty());
	}

	public static final boolean isBlank(Set<Object> obj) {
		return (obj == null) || (obj.isEmpty());
	}

	public static final boolean isBlank(Map<Object, Object> map) {
		return (map == null) || (map.isEmpty());
	}

	public static final boolean isBlank(Hashtable<Object, Object> hashtable) {
		return (hashtable == null) || (hashtable.isEmpty());
	}

	public static final boolean isBlank(List<?> list) {
		return (list == null) || (list.isEmpty());
	}
}
