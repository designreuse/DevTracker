package com.tmt.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Utils {
	
	public static final String SEPARATOR = " ";

	public static String showBean(Object bean, boolean showNulls) {
		if (bean == null)
			return null;
		StringBuilder sb = new StringBuilder(bean.getClass().getName())
				.append("[");
		try {
			BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] pd = bi.getPropertyDescriptors();
			for (int i = 0; i < pd.length; i++) {
				if (!"class".equals(pd[i].getName())) {
					Object result = pd[i].getReadMethod().invoke(bean);
					if (showNulls || result != null) {
						sb.append(pd[i].getDisplayName()).append("=")
								.append(result);
						if (i == pd.length - 1)
							continue;
						sb.append(",");
					}
				}
			}
		} catch (Exception ex) {
		}

		return sb.append("]").toString();
	}

	public static int generate5DigtRandomNum(){
		 Random r = new Random();
		 int ran5DigtNum = r.nextInt(99999);
		 return ran5DigtNum;
	}
	
	private static String parent_id;
		
	public static void main(String... arg) {
		System.out.println(getDateTime());
	}

	public static String getParent_id() {
		return parent_id;
	}

	public static void setParent_id(String parent_id) {
		Utils.parent_id = parent_id;
	}

	
	public static String getDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
		Date date = new Date();	
		return dateFormat.format(date);
	}
	
	public static String getSysTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();	
		return dateFormat.format(date);
	}
	
	public static String getCombinedDateTime(){
		String dateAndTime = getDateTime() +SEPARATOR+ getSysTime();
		return dateAndTime;
	}
	
	public static String getYesterdayDateString(){		
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -1);    
	        return dateFormat.format(cal.getTime());
	}
	
	
	public static Object trimReflective(Object object) throws Exception {
		if (object == null)
			return null;

		Class<? extends Object> c = object.getClass();
		try {
			// Introspector usage to pick the getters conveniently thereby
			// excluding the Object getters
			for (PropertyDescriptor propertyDescriptor : Introspector
					.getBeanInfo(c, Object.class).getPropertyDescriptors()) {
				Method method = propertyDescriptor.getReadMethod();
				String name = method.getName();

				// If the current level of Property is of type String
				if (method.getReturnType().equals(String.class)) {
					String property = (String) method.invoke(object);
					if (property != null) {
						Method setter = c.getMethod("set" + name.substring(3),
								new Class<?>[] { String.class });
						if (setter != null)
							// Setter to trim and set the trimmed String value
							setter.invoke(object, property.trim());
					}
				}

				// If an Object Array of Properties - added additional check to
				// avoid getBytes returning a byte[] and process
				if (method.getReturnType().isArray()
						&& !method.getReturnType().isPrimitive()
						&& !method.getReturnType().equals(String[].class)
						&& !method.getReturnType().equals(byte[].class)) {
					System.out.println(method.getReturnType());
					// Type check for primitive arrays (would fail typecasting
					// in case of int[], char[] etc)
					if (method.invoke(object) instanceof Object[]) {
						Object[] objectArray = (Object[]) method.invoke(object);
						if (objectArray != null) {
							for (Object obj : (Object[]) objectArray) {
								// Recursively revisit with the current property
								trimReflective(obj);
							}
						}
					}
				}
				// If a String array
				if (method.getReturnType().equals(String[].class)) {
					String[] propertyArray = (String[]) method.invoke(object);
					if (propertyArray != null) {
						Method setter = c.getMethod("set" + name.substring(3),
								new Class<?>[] { String[].class });
						if (setter != null) {
							String[] modifiedArray = new String[propertyArray.length];
							for (int i = 0; i < propertyArray.length; i++)
								if (propertyArray[i] != null)
									modifiedArray[i] = propertyArray[i].trim();

							// Explicit wrapping
							setter.invoke(object,
									new Object[] { modifiedArray });
						}
					}
				}
				// Collections start
				if (Collection.class.isAssignableFrom(method.getReturnType())) {
					Collection collectionProperty = (Collection) method
							.invoke(object);
					if (collectionProperty != null) {
						for (int index = 0; index < collectionProperty.size(); index++) {
							if (collectionProperty.toArray()[index] instanceof String) {
								String element = (String) collectionProperty
										.toArray()[index];

								if (element != null) {
									// Check if List was created with
									// Arrays.asList (non-resizable Array)
									if (collectionProperty instanceof List) {
										((List) collectionProperty).set(index,
												element.trim());
									} else {
										collectionProperty.remove(element);
										collectionProperty.add(element.trim());
									}
								}
							} else {
								// Recursively revisit with the current property
								trimReflective(collectionProperty.toArray()[index]);
							}
						}
					}
				}
				// Separate placement for Map with special conditions to process
				// keys and values
				if (method.getReturnType().equals(Map.class)) {
					Map mapProperty = (Map) method.invoke(object);
					if (mapProperty != null) {
						// Keys
						for (int index = 0; index < mapProperty.keySet().size(); index++) {
							if (mapProperty.keySet().toArray()[index] instanceof String) {
								String element = (String) mapProperty.keySet()
										.toArray()[index];
								if (element != null) {
									mapProperty.put(element.trim(),
											mapProperty.get(element));
									mapProperty.remove(element);
								}
							} else {
								// Recursively revisit with the current property
								trimReflective(mapProperty.get(index));
							}

						}
						// Values
						for (Map.Entry entry : (Set<Map.Entry>) mapProperty
								.entrySet()) {

							if (entry.getValue() instanceof String) {
								String element = (String) entry.getValue();
								if (element != null) {
									entry.setValue(element.trim());
								}
							} else {
								// Recursively revisit with the current property
								trimReflective(entry.getValue());
							}
						}
					}
				} else {// Catch a custom data type as property and send through
						// recursion
					Object property = (Object) method.invoke(object);
					if (property != null) {
						trimReflective(property);
					}
				}
			}

		} catch (Exception e) {
			throw new Exception("Strings cannot be trimmed because: ", e);
		}

		return object;

	}
	
	
	
}
