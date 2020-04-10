package com.example.feign.utul;


import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

public class MapUtil {

	public static Map<String, String> getUrlParams(String param) {
		Map<String, String> map = new HashMap<String, String>(0);
		if (StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url
	 * 
	 * @param paramValues
	 * @return
	 */
	public static String getParamsOrderByKey(Map<String, String> paramValues) {
		String params = "";
		Set<String> key = paramValues.keySet();
		List<String> paramNames = new ArrayList<String>(paramValues.size());
		paramNames.addAll(paramValues.keySet());
		Collections.sort(paramNames);
		for (String paramName : paramNames) {

			if (params.equals("")) {
				params += paramName + "=" + paramValues.get(paramName);
			} else {
				params += "&" + paramName + "=" + paramValues.get(paramName);
			}
		}

		return params;
	}

	public static Map<String, String> getUrlParameter(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		if(MapUtils.isNotEmpty(params)){
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					queryString += key + "=" + value + "&";
				}
			}
			// 去掉最后一个空格
			queryString = queryString.substring(0, queryString.length() - 1);
		}
		Map<String, String> map = MapUtil.getUrlParams(queryString);
		return map;
	}

	/**
	 * 将Map对象通过反射机制转换成Bean对象
	 *
	 * @param map
	 *            存放数据的map对象
	 * @param clazz
	 *            待转换的class
	 * @return 转换后的Bean对象
	 * @throws Exception
	 *             异常
	 */
	public static Object mapToBean(Map<String, Object> map, Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String propertyName = entry.getKey(); // 属性名
				Object value = entry.getValue();
				String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
				Field field = getClassField(clazz, propertyName);
				if (field == null)
					continue;
				Class<?> fieldTypeClass = field.getType();
				value = convertValType(value, fieldTypeClass);
				try {
					clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	/**
	 * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
	 *
	 * @param clazz
	 *            指定的class
	 * @param fieldName
	 *            字段名称
	 * @return Field对象
	 */
	private static Field getClassField(Class<?> clazz, String fieldName) {
		if (Object.class.isAssignableFrom(clazz)) {
			return null;
		}
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null) {// 简单的递归一下
			return getClassField(superClass, fieldName);
		}
		return null;
	}

	/**
	 * 将Object类型的值，转换成bean对象属性里对应的类型值
	 *
	 * @param value
	 *            Object对象值
	 * @param fieldTypeClass
	 *            属性的类型
	 * @return 转换后的值
	 */
	private static Object convertValType(Object value, Class<?> fieldTypeClass) {
		Object retVal = null;
		if (Long.class.isAssignableFrom(fieldTypeClass) || long.class.isAssignableFrom(fieldTypeClass)) {
			retVal = Long.parseLong(value.toString());
		} else if (Integer.class.isAssignableFrom(fieldTypeClass) || int.class.isAssignableFrom(fieldTypeClass)) {
			retVal = Integer.parseInt(value.toString());
		} else if (Float.class.isAssignableFrom(fieldTypeClass) || float.class.isAssignableFrom(fieldTypeClass)) {
			retVal = Float.parseFloat(value.toString());
		} else if (Double.class.isAssignableFrom(fieldTypeClass) || double.class.isAssignableFrom(fieldTypeClass)) {
			retVal = Double.parseDouble(value.toString());
		} else {
			retVal = value;
		}
		return retVal;
	}
	
}
