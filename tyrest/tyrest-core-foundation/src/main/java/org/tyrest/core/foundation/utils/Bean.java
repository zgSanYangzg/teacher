package org.tyrest.core.foundation.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tyrest.core.foundation.exceptions.BusinessException;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: Bean.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:普通javaBean的工具类，包含对Bean的属性的各种操作
 *  TODO
 * 
 *  Notes:
 *  $Id: Bean.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class Bean {

	/* 获取对象的属性描述，如果此属性没有getter或setter方法，则此属性的描述不存在 */
	public  static PropertyDescriptor getPropertyDescriptor(String propertyName, Class<?> beanClass) {
		PropertyDescriptor propertyDescriptor = null;
		try {
			propertyDescriptor = new PropertyDescriptor(propertyName, beanClass);
		} catch (IntrospectionException e) {
		}
		return propertyDescriptor;
	}

	/* 获取类及其父类的字段定义 */
	private static Map<String, Class<?>> getFieldsMap(Class<?> beanClass) {
		Field[] fields = beanClass.getDeclaredFields();
		Map<String, Class<?>> fieldMap = new HashMap<String, Class<?>>();
		for (Field field : fields) {
			fieldMap.put(field.getName(), field.getType());
		}
		if (beanClass.getSuperclass() != null) {
			fieldMap.putAll(getFieldsMap(beanClass.getSuperclass()));
		}
		return fieldMap;
	}

	/* 获取类及其父类的Field对象列表 */
	private static List<Field> getAllFields(Class<?> beanClass) {
		List<Field> result = new ArrayList<Field>();
		result.addAll(Arrays.asList(beanClass.getDeclaredFields()));
		if (beanClass.getSuperclass() != null) {
			result.addAll(getAllFields(beanClass.getSuperclass()));
		}
		return result;
	}

	/**
	 * TODO. 将source中不为空的字段值复制到对应的taget中的字段
	 * 
	 * @param sourceBean
	 * @param targetBean
	 * @return
	 * @throws Exception
	 */
	public static <S, T> T copyExistPropertis(S sourceBean, T targetBean) throws Exception {
		if (sourceBean != null && targetBean != null) {
			Map<String, Class<?>> fields = getFieldsMap(sourceBean.getClass());
			Class<?> sourceBeanClass = sourceBean.getClass();
			Class<?> targetBeanClass = targetBean.getClass();
			PropertyDescriptor currentSourcePd = null;
			PropertyDescriptor currentTargetPd = null;
			Object sourcePropertyValue = null;

			for (String fieldName : fields.keySet()) {
				if (fieldName.equals("serialVersionUID"))
					continue;

				currentSourcePd = getPropertyDescriptor(fieldName, sourceBeanClass);
				if (!ValidationUtil.isEmpty(currentSourcePd)) {
					sourcePropertyValue = currentSourcePd.getReadMethod().invoke(sourceBean);
				}
				currentTargetPd = getPropertyDescriptor(fieldName, targetBeanClass);
				if (!ValidationUtil.isEmpty(currentTargetPd)) {
					if (currentTargetPd.getPropertyType().equals(String.class)) {
						if (sourcePropertyValue != null) {
							currentTargetPd.getWriteMethod().invoke(targetBean, sourcePropertyValue);
						}
					} else {
						if (!ValidationUtil.isEmpty(sourcePropertyValue)) {
							currentTargetPd.getWriteMethod().invoke(targetBean, sourcePropertyValue);
						}
					}
				}
			}
		}
		return targetBean;
	}
	
	public static <M,E> E toPo(M model, E entity) throws Exception {
		if(model != null && entity != null) copyExistPropertis(model, entity);
		return entity;
	}

	public static <M,E> M toModel(E entity, M model) throws Exception {
		if(model != null && entity != null) copyExistPropertis(entity, model);
		return model;
	}

	public static <M,E> List<M> toModels(List<E> entities,Class<M> modelBeanClass) throws Exception {
		List<M> modelList = new ArrayList<M>();
		M currentModel = null;
		for (E entity : entities) {
			currentModel = modelBeanClass.newInstance();
			modelList.add(toModel(entity,currentModel));
		}
		return modelList;
	}

	/**
	 * TODO.将数据库查询的结果集转换为自定义对象
	 * 
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToBean(Map<String, Object> map, Class<?> beanClass) throws Exception {
		Map<String, Class<?>> fieldsMap = getFieldsMap(beanClass);
		Object result = beanClass.newInstance();
		Object currentMapValue = null;
		PropertyDescriptor currentPd = null;
		for (String fieldName : fieldsMap.keySet()) {
			if (fieldName.equals("serialVersionUID"))
				continue;
			currentMapValue = map.get(fieldName);
			if (!ValidationUtil.isEmpty(currentMapValue)) {
				if (currentMapValue instanceof BigDecimal) {
					currentMapValue = ((BigDecimal) currentMapValue).doubleValue();
				}
				if (currentMapValue instanceof BigInteger) {
					currentMapValue = ((java.math.BigInteger) currentMapValue).longValue();
				}
				currentPd = getPropertyDescriptor(fieldName, beanClass);
				if (!ValidationUtil.isEmpty(currentPd)) {
					try {
						currentPd.getWriteMethod().invoke(result, currentMapValue);
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			}
		}
		return result;
	}
	/**
	 * TODO.将数据库查询的结果集转换为自定义对象结果集
	 * 
	 * @param listMap
	 * @param T
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> listMap2ListBean(List<Map<String, Object>> listMap, Class<T> T) throws Exception {
		List<T> list = new ArrayList<T>();
		if(!ValidationUtil.isEmpty(listMap)){
			for(Map<String, Object> map : listMap){
				list.add((T) Bean.mapToBean(map, T));
			}
		}
		return list;
	}

	/**
	 * TODO.将javaBean及其父对象的属性值转换为Map
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static <T> Map<String, Object> BeantoMap(T bean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Field> fields = getAllFields(bean.getClass());
		for (int i = 0; i < fields.size(); i++) {
			String fieldName = fields.get(i).getName();
			if (fieldName.equals("serialVersionUID"))
				continue;
			map.put(fieldName, getPropertyValue(fieldName, bean));
		}
		return map;
	}

	/**
	 * 
	 * 列表转map
	 *
	 * @param list
	 * @param keyProperty
	 *            作为key的属性名称
	 * @param clzz
	 * @return
	 * @throws Exception
	 */
	public static <T> Map<Object, T> listToMap(List<T> list, String keyProperty, Class<?> beanClass) throws Exception {
		Map<Object, T> returnMap = new HashMap<Object, T>();
		PropertyDescriptor currentPd = null;
		for (T t : list) {
			currentPd = getPropertyDescriptor(keyProperty, beanClass);
			if (!ValidationUtil.isEmpty(currentPd)) {
				Object obj = currentPd.getReadMethod().invoke(t);
				if (!ValidationUtil.isEmpty(obj)) {
					returnMap.put(obj, t);
				}
			}
		}
		return returnMap;
	}

	/**
	 * 列表转map
	 * 
	 * @param list
	 *            对象列表
	 * @param keyProperty
	 *            指定的属性为key
	 * @param valueProperty
	 *            指定属性为 value
	 * @param clzz
	 *            实体类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <K, T> Map<K, Object> listToMap(List<T> list, String keyProperty, String valueProperty,
			Class<?> beanClass) throws Exception {
		Map<K, Object> returnMap = new HashMap<K, Object>();
		for (T t : list) {
			K key = null;
			Object keyObject = getPropertyValue(keyProperty, t);
			if (!ValidationUtil.isEmpty(keyObject)) {
				key = (K) keyObject;
			} else {
				throw new RuntimeException("the key property value is can not be null." + keyProperty);
			}

			Object valueObject = getPropertyValue(valueProperty, t);
			returnMap.put(key, valueObject);
		}

		return returnMap;
	}

	public static <T> Object getPropertyValue(String propertyName, T bean) throws Exception {
		PropertyDescriptor currentPd = getPropertyDescriptor(propertyName, bean.getClass());
		Object propertyValue = null;
		if (!ValidationUtil.isEmpty(currentPd)) {
			propertyValue = currentPd.getReadMethod().invoke(bean);
		} else {
			throw new RuntimeException("property not found: " + propertyName + "in Class:" + bean.getClass().getName());
		}
		return propertyValue;
	}

	public static <T> Object setPropertyValue(String propertyName, Object propertyValue, T bean) throws Exception {
		PropertyDescriptor currentPd = getPropertyDescriptor(propertyName, bean.getClass());
		if (!ValidationUtil.isEmpty(currentPd)) {
			currentPd.getWriteMethod().invoke(bean, propertyValue);
		}
		return propertyValue;
	}

	/**
	 * 
	 * @param data
	 * @param dataType
	 * @return
	 * @throws BusinessException
	 */
	public static String dataTypeConvertToString(Object data, String dataTypeStr) throws BusinessException {
		String value = "";
		DataType dateType = DataType.getInstance(dataTypeStr);
		if (!ValidationUtil.isEmpty(dateType)) {
			switch (dateType) {
			case DATETIME:
				if (!ValidationUtil.isEmpty(data)) {
					value = DateTimeUtil.format((Date) data, DateTimeUtil.ISO_DATE_HOUR24_MIN_SEC);
				}
				break;
			case DOUBLE:
			case INT:
				value = String.valueOf(ValidationUtil.isEmpty(data) ? "0" : data);
				break;
			case VARCHAR:
				if (!ValidationUtil.isEmpty(data)) {
					value = data.toString();
				}
				break;
			default:
				value = "";
			}
		} else {
			throw new BusinessException("数据类型有误.data:" + data + ";dataType:" + dataTypeStr);
		}

		return value;
	}

	/**
	 * 
	 * @param data
	 * @param dataType
	 * @return
	 * @throws BusinessException
	 */
	public static Object dataTypeConvertFromString(String data, String dataTypeStr) throws BusinessException {
		DataType dateType = DataType.getInstance(dataTypeStr);
		if (!ValidationUtil.isEmpty(dateType)) {
			if (!ValidationUtil.isEmpty(data)) {
				switch (dateType) {
				case DATETIME:
					return DateTimeUtil.format(data, DateTimeUtil.ISO_DATE_HOUR24_MIN_SEC);
				case DOUBLE:
					return Double.parseDouble(data);
				case INT:
					return Integer.parseInt(data);
				case VARCHAR:
					return data;
				default:
				}
			}
		} else {
			throw new BusinessException("数据类型有误.");
		}

		return null;
	}



    private enum DataType {
		VARCHAR, DATETIME, INT, DOUBLE;
		public static DataType getInstance(String str) {
			DataType dataType = null;
			for (DataType value : DataType.values()) {
				if (value.name().equals(str)) {
					dataType = value;
				}
			}
			return dataType;
		}
	}

}
