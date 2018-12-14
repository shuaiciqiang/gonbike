package com.gonbike.common.dbUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据对象，获取对应的SQL语句,所有Class对应的表名即为类名复数，所有表主键名必须是Id。
 *  sql非法注入的问题暂不 考虑
 * @author Shuaige
 *
 */
public class DbUtil {

	/**
	 * 表前缀，固定不变
	 */
	public final static String table = "t_";

	/**
	 * 单条记录查询SQL
	 * 
	 * @param <T>
	 * @param clazz
	 *            类
	 * @param queryObject
	 *            传入的查询对象，是一个bean
	 * @return 直接返回可执行的单条SQL
	 */
	public static <T> String getSelectSql(Class clazz, T queryObject) {
		List<Field> fieldList = getClassAllField(clazz);
		StringBuffer vBuff = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		vBuff.append("Select ");
		for (Field field : fieldList) {
			// System.out.println(field.getName());
			field.setAccessible(true);		
			DbDate vDbDate=field.getAnnotation(DbDate.class);
			if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
				try {
					String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
					map.put(field.getName(), type);// 将类属性和类型缓存起来，备用
					if (vDbDate != null) {
						vBuff.append("DATE_FORMAT("+field.getName()+",'%Y-%m-%d %H:%i:%s') as "+ field.getName() + ", ");
					} else {
						vBuff.append(field.getName() + ", ");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		vBuff.append("from " + table + Inflector.getInstance().pluralize(clazz.getSimpleName()) + " where 1=1 ");

		// 开始拼接查询条件，解析queryObject
		List<Field> queryFieldList = getClassAllField(queryObject.getClass());
		for (Field field : queryFieldList) {
			// System.out.println(field.getName());
			DbDate vDbDate=field.getAnnotation(DbDate.class);
			 
			field.setAccessible(true);
			if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
				try {
					
					if (field.getName().equals("take") || field.getName().equals("skip")) {

					} else {
						Object value = field.get(queryObject);// 获取该属性的值
						String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
						if (value != null) {
							if (type.equals("string[]")) {
								//是数组集合类型的字符串
								String[] vStrs=(String[])value;
								String key=Inflector.getInstance().singularize(field.getName());
								//System.out.println(key+"数据类型是："+type);
								vBuff.append(" and "+key+" in (");
								for(String vStr:vStrs) {
									if (vStr!=null) {
										vBuff.append("'"+vStr+"',");
									}
								}
								vBuff.append(")");
							}else if (type.equals("integer[]")) {
								//是数组集合类型的数字
								Integer[] vInts=(Integer[])value;
								String key=Inflector.getInstance().singularize(field.getName());
								//System.out.println(key+"数据类型是："+type);
								vBuff.append(" and "+key+" in (");
								for(Integer vStr:vInts) {
									if (vStr!=null) {
										vBuff.append(""+vStr+",");
									}
								}
								vBuff.append(")");
							}else if (type.equals("list")) {
								//是数组集合类型的数据
								List vList=(List)value;
								String key=Inflector.getInstance().singularize(field.getName());
								//System.out.println(key+"数据类型是："+type);
								vBuff.append(" and "+key+" in (");
								for(Object vStr:vList) {
									if (vStr!=null) {
										vBuff.append("'"+vStr+"',");
									}
								}
								vBuff.append(")");
							}else {	
								if (field.getName().endsWith("Contains")) {
									// like
									String key = field.getName().replaceAll("Contains", "");
									if (checkFieldInMap(key, map)) {
										vBuff.append(" and " + key + " like '%" + value + "%' ");
									}
								} else if (field.getName().endsWith("From")) {
									String key = field.getName().replaceAll("From", "");
									if (checkFieldInMap(key, map)) {
										if (vDbDate!=null) {
											vBuff.append(" and " + key + " >= Str_To_Date('" + value + "',''%Y-%m-%d %H:%i:%s'') ");
										}else {
											vBuff.append(" and " + key + " >= '" + value + "' ");
										}
									}
								} else if (field.getName().endsWith("To")) {
									String key = field.getName().replaceAll("To", "");
									if (checkFieldInMap(key, map)) {
										if (vDbDate!=null) {
											vBuff.append(" and " + key + " <= Str_To_Date('" + value + "',''%Y-%m-%d %H:%i:%s'') ");
	
										}else {
											vBuff.append(" and " + key + " <= '" + value + "' ");
										}
									}
								} else if (field.getName().endsWith("IsNull")) {
									String key = field.getName().replaceAll("IsNull", "");
									if (checkFieldInMap(key, map)) {
										if (value.toString().equals("true")) {
											vBuff.append(" and " + key + " is null ");
										} else {
											vBuff.append(" and " + key + " is not null ");
										}
									}
								} else if (field.getName().endsWith("NotEquals")) {
									String key = field.getName().replaceAll("NotEquals", "");
									if (checkFieldInMap(key, map)) {
										vBuff.append(" and " + key + " != '" + value + "' ");
									}
								} else {
									// 要判断该字段名是否存在与bean类
									if (checkFieldInMap(field.getName(), map)) {
										vBuff.append(" and " + field.getName() + " = '" + value + "' ");
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		vBuff.append(" limit 0,1");
		return sqlReplace(vBuff.toString());// 处理掉select语句的SQL中最后一个字段逗号问题
	}

	/**
	 * 分页查询SQL
	 * 
	 * @param <T>
	 * @param clazz
	 *            类
	 * @param queryObject
	 *            传入的查询对象，是一个bean
	 * @param orderBy
	 *            排序字段默认是ID
	 * @return 直接返回可执行的单条SQL
	 */
	public static <T> String getSelectPageSql(Class clazz, T queryObject, String orderBy) {
		if (orderBy == null) {
			orderBy = "Id";
		}
		List<Field> fieldList = getClassAllField(clazz);
		Map<String, String> map = new HashMap<String, String>();
		String take = "";
		String skip = "";
		StringBuffer vBuff = new StringBuffer();
		vBuff.append("select * from (Select ");
		for (Field field : fieldList) {
			// System.out.println(field.getName());
			field.setAccessible(true);
			DbDate vDbDate=field.getAnnotation(DbDate.class);
			if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
				try {
					String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
					map.put(field.getName(), type);// 将类属性和类型缓存起来，备用
					if (vDbDate != null) {
						vBuff.append("DATE_FORMAT("+field.getName()+",'%Y-%m-%d %H:%i:%s') as "+ field.getName() + ", ");
					} else {
						vBuff.append(field.getName() + ", ");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		vBuff.append("from " + table + Inflector.getInstance().pluralize(clazz.getSimpleName()) + " where 1=1 ");
		// 开始拼接查询条件，解析queryObject
		List<Field> queryFieldList = getClassAllField(queryObject.getClass());
		for (Field field : queryFieldList) {
			// System.out.println(field.getName());
			field.setAccessible(true);
			DbDate vDbDate=field.getAnnotation(DbDate.class);
			if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
				try {
					Object value = field.get(queryObject);// 获取该属性的值
					if (field.getName().equals("take")) {
						take = value + "";
					} else if (field.getName().equals("skip")) {
						skip = value + "";
					} else {
						String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
						
						if (value != null) {
							if (type.equals("string[]")) {
								//是数组集合类型的字符串
								String[] vStrs=(String[])value;
								String key=Inflector.getInstance().singularize(field.getName());
								//System.out.println(key+"数据类型是："+type);
								vBuff.append(" and "+key+" in (");
								for(String vStr:vStrs) {
									if (vStr!=null) {
										vBuff.append("'"+vStr+"',");
									}
								}
								vBuff.append(")");
							}else if (type.equals("integer[]")) {
								//是数组集合类型的数字
								Integer[] vInts=(Integer[])value;
								String key=Inflector.getInstance().singularize(field.getName());
								//System.out.println(key+"数据类型是："+type);
								vBuff.append(" and "+key+" in (");
								for(Integer vStr:vInts) {
									if (vStr!=null) {
										vBuff.append(""+vStr+",");
									}
								}
								vBuff.append(")");
							}else if (type.equals("list")) {
								//是数组集合类型的数据
								List vList=(List)value;
								String key=Inflector.getInstance().singularize(field.getName());
								//System.out.println(key+"数据类型是："+type);
								vBuff.append(" and "+key+" in (");
								for(Object vStr:vList) {
									if (vStr!=null) {
										vBuff.append("'"+vStr+"',");
									}
								}
								vBuff.append(")");
							}else {				
								
								if (field.getName().endsWith("Contains")) {
									// like
									String key = field.getName().replaceAll("Contains", "");
									if (checkFieldInMap(key, map)) {
										vBuff.append(" and " + key + " like '%" + value + "%' ");
									}
								} else if (field.getName().endsWith("From")) {
									String key = field.getName().replaceAll("From", "");
									if (checkFieldInMap(key, map)) {
										if (vDbDate!=null) {
											vBuff.append(" and " + key + " >= Str_To_Date('" + value + "',''%Y-%m-%d %H:%i:%s'') ");
	
										}else {
											vBuff.append(" and " + key + " >= '" + value + "' ");
										}
									}
								} else if (field.getName().endsWith("To")) {
									String key = field.getName().replaceAll("To", "");
									if (checkFieldInMap(key, map)) {
										if (vDbDate!=null) {
											vBuff.append(" and " + key + " >= Str_To_Date('" + value + "',''%Y-%m-%d %H:%i:%s'') ");
	
										}else {
											vBuff.append(" and " + key + " <= '" + value + "' ");
										}
									}
								} else if (field.getName().endsWith("IsNull")) {
									String key = field.getName().replaceAll("IsNull", "");
									if (checkFieldInMap(key, map)) {
										if (value.toString().equals("true")) {
											vBuff.append(" and " + key + " is null ");
										} else {
											vBuff.append(" and " + key + " is not null ");
										}
									}
								} else if (field.getName().endsWith("NotEquals")) {
									String key = field.getName().replaceAll("NotEquals", "");
									if (checkFieldInMap(key, map)) {
										vBuff.append(" and " + key + " != '" + value + "' ");
									}
								} else {
									// 要判断该字段名是否存在与bean类
									if (checkFieldInMap(field.getName(), map)) {
										vBuff.append(" and " + field.getName() + " = '" + value + "' ");
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		vBuff.append(" order by " + orderBy + " ");
		vBuff.append(") where 1=1 ");// 查询条件应该先放这里？
		vBuff.append("limit " + skip + " , " + take + " ");
		return sqlReplace(vBuff.toString());// 处理掉select语句的SQL中最后一个字段逗号问题
	}

	/**
	 * 分页查询功能，默认按Id排序
	 * 
	 * @param clazz
	 * @param queryObject
	 * @return
	 */
	public static <T> String getSelectPageSql(Class clazz, T queryObject) {
		return getSelectPageSql(clazz, queryObject, "Id");
	}

	/**
	 * 根据传入的对象，返回对应的insert语句，如果属性值为null，则insert语句中不生成对应的属性
	 * 
	 * @param <T>
	 *            单个bean对象。暂不支持list传入
	 * @param object
	 *            带属性值的bean对象
	 * @return 直接返回完整可执行的SQL语句
	 */
	public static <T> String getInsertSql(T object) {
		if (object == null) {
			return null;
		}
		Class clazz = object.getClass();
		List<Field> fieldList = getClassAllField(clazz);
		StringBuffer vBuff = new StringBuffer();
		StringBuffer vBuffField = new StringBuffer();
		StringBuffer vBuffValues = new StringBuffer();
		vBuff.append("insert into ");
		vBuff.append(table + Inflector.getInstance().pluralize(clazz.getSimpleName()));
		vBuff.append(" (");
		for (Field field : fieldList) {
			field.setAccessible(true);
			DbDate vDbDate=field.getAnnotation(DbDate.class);
			if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
				try {
					Object value = field.get(object);// 获取该属性的值
					String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
					if (value != null) {
						vBuffField.append(field.getName() + ", ");
						if ("string".equals(type)) {
							// 字符串
							vBuffValues.append("'" + value + "', ");
						} else if (("int".equals(type)) || "integer".equals(type)) {
							// 数字型
							vBuffValues.append(value + ", ");
						} else if ("float".equals(type)) {
							// 数字型
							vBuffValues.append(value + ", ");
						} else if ("double".equals(type)) {
							// 数字型
							vBuffValues.append(value + ", ");
						} else if ("date".equals(type)) {
							// 日期型
							vBuffValues.append("'" + value + "', ");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		vBuff.append(vBuffField.toString().substring(0, vBuffField.toString().length() - 2));
		vBuff.append(") values (");
		vBuff.append(vBuffValues.toString().substring(0, vBuffValues.toString().length() - 2));
		vBuff.append(")");
		return sqlReplace(vBuff.toString());
	}

	/**
	 * 返回对应的update语句,ID默认是主键，不更新
	 * 
	 * @param object
	 * @param whereField
	 *            是更新的条件字段名
	 * @return 返回的是可执行的SQL。
	 */
	public static <T> String getUpdateSql(T object, T queryObject) {
		if (object == null) {
			return null;
		}
		Class clazz = object.getClass();
		List<Field> fieldList = getClassAllField(clazz);
		StringBuffer vBuff = new StringBuffer();
		StringBuffer vBuffField = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		String whereValue = "";
		vBuff.append("update ");
		vBuff.append(table + Inflector.getInstance().pluralize(clazz.getSimpleName()));
		vBuff.append(" set ");
		for (Field field : fieldList) {
			field.setAccessible(true);
			DbDate vDbDate=field.getAnnotation(DbDate.class);
			if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
				try {
					//System.out.println(field.getName());
					if (field.getName().toLowerCase().equals("id")) {
						//ID是主键，默认不更新
						Object value = field.get(object);// 获取该属性的值
						if (value != null) {
							whereValue = String.valueOf(value);
						}
					} else {
						Object value = field.get(object);// 获取该属性的值
						String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
						map.put(field.getName(), type);
						if (field.getName().toLowerCase().equals("id")) {
							if (value != null) {
								whereValue = String.valueOf(value);
							}
						}
						if (value != null) {
							if (value.toString().trim().equals("")) {
								// 说明是要更新为空，传入的是" ",则替换为null
								vBuff.append(field.getName() + " = null ,");
							} else {
								if (type.equals("string")) {
									vBuff.append(field.getName() + " = '" + value + "', ");
								} else if (type.equals("date")) {
									vBuff.append(field.getName() + " = '" + value + "', ");
								} else {
									vBuff.append(field.getName() + " = " + value + ", ");
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		vBuff.append("where 1=0");
		// 开始拼接查询条件，解析queryObject
				List<Field> queryFieldList = getClassAllField(queryObject.getClass());
				for (Field field : queryFieldList) {
					// System.out.println(field.getName());
					field.setAccessible(true);
					DbDate vDbDate=field.getAnnotation(DbDate.class);
					if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
						try {
							if (field.getName().equals("take") || field.getName().equals("skip")) {

							} else {
								Object value = field.get(queryObject);// 获取该属性的值
								String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
								if (value != null) {
									if (type.equals("string[]")) {
										//是数组集合类型的字符串
										String[] vStrs=(String[])value;
										String key=Inflector.getInstance().singularize(field.getName());
										//System.out.println(key+"数据类型是："+type);
										vBuffField.append(" and "+key+" in (");
										for(String vStr:vStrs) {
											if (vStr!=null) {
												vBuffField.append("'"+vStr+"',");
											}
										}
										vBuffField.append(")");
									}else if (type.equals("integer[]")) {
										//是数组集合类型的数字
										Integer[] vInts=(Integer[])value;
										String key=Inflector.getInstance().singularize(field.getName());
										//System.out.println(key+"数据类型是："+type);
										vBuffField.append(" and "+key+" in (");
										for(Integer vStr:vInts) {
											if (vStr!=null) {
												vBuffField.append(""+vStr+",");
											}
										}
										vBuffField.append(")");
									}else if (type.equals("list")) {
										//是数组集合类型的数据
										List vList=(List)value;
										String key=Inflector.getInstance().singularize(field.getName());
										//System.out.println(key+"数据类型是："+type);
										vBuffField.append(" and "+key+" in (");
										for(Object vStr:vList) {
											if (vStr!=null) {
												vBuffField.append("'"+vStr+"',");
											}
										}
										vBuffField.append(")");
									} else {
										if (field.getName().endsWith("Contains")) {
									 
										// like
										String key = field.getName().replaceAll("Contains", "");
										if (checkFieldInMap(key, map)) {
											vBuffField.append(" and " + key + " like '%" + value + "%' ");
										}
									} else if (field.getName().endsWith("From")) {
										String key = field.getName().replaceAll("From", "");
										if (checkFieldInMap(key, map)) {
											vBuffField.append(" and " + key + " >= '" + value + "' ");
										}
									} else if (field.getName().endsWith("To")) {
										String key = field.getName().replaceAll("To", "");
										if (checkFieldInMap(key, map)) {
											vBuffField.append(" and " + key + " <= '" + value + "' ");
										}
									} else if (field.getName().endsWith("IsNull")) {
										String key = field.getName().replaceAll("IsNull", "");
										if (checkFieldInMap(key, map)) {
											if (value.toString().equals("true")) {
												vBuffField.append(" and " + key + " is null ");
											} else {
												vBuffField.append(" and " + key + " is not null ");
											}
										}
									} else if (field.getName().endsWith("NotEquals")) {
										String key = field.getName().replaceAll("NotEquals", "");
										if (checkFieldInMap(key, map)) {
											vBuffField.append(" and " + key + " != '" + value + "' ");
										}
									} else {
										// 要判断该字段名是否存在与bean类
										if (checkFieldInMap(field.getName(), map)) {
											vBuffField.append(" and " + field.getName() + " = '" + value + "' ");
										}
									}
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				String whereField = vBuffField.toString();
				if (whereField != null && !whereField.trim().equals("") && !whereField.trim().equals("and")) {

					vBuff.append(whereField);
				}

		return sqlReplace(vBuff.toString());
	}

	/**
	 * 单条记录查询SQL
	 * 
	 * @param <T>
	 * @param clazz
	 *            类
	 * @param queryObject
	 *            传入的查询对象，是一个bean
	 * @return 直接返回可执行的单条SQL
	 */
	public static <T> String getDeleteSql(Class clazz, T queryObject) {
		List<Field> fieldList = getClassAllField(clazz);
		StringBuffer vBuff = new StringBuffer();
		StringBuffer vBuffField = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		for (Field field : fieldList) {
			// System.out.println(field.getName());
			field.setAccessible(true);
			if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
				try {
					String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
					map.put(field.getName(), type);// 将类属性和类型缓存起来，备用
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		vBuff.append("delete from " + table + Inflector.getInstance().pluralize(clazz.getSimpleName()) + " where 1=0 ");

		// 开始拼接查询条件，解 析queryObject
		List<Field> queryFieldList = getClassAllField(queryObject.getClass());
		for (Field field : queryFieldList) {
			// System.out.println(field.getName());
			field.setAccessible(true);
			DbDate vDbDate=field.getAnnotation(DbDate.class);
			if (field.getName() != null && !field.getName().equals("serialVersionUID")) {
				try {
					if (field.getName().equals("take") || field.getName().equals("skip")) {

					} else {
						Object value = field.get(queryObject);// 获取该属性的值
						String type = field.getType().getSimpleName().toLowerCase();// 获取属性类型,String,Integer,Long,
						if (value != null) {
							if (type.equals("string[]")) {
								//是数组集合类型的字符串
								String[] vStrs=(String[])value;
								String key=Inflector.getInstance().singularize(field.getName());
								//System.out.println(key+"数据类型是："+type);
								vBuffField.append(" and "+key+" in (");
								for(String vStr:vStrs) {
									if (vStr!=null) {
										vBuffField.append("'"+vStr+"',");
									}
								}
								vBuffField.append(")");
							}else if (type.equals("integer[]")) {
								//是数组集合类型的数字
								Integer[] vInts=(Integer[])value;
								String key=Inflector.getInstance().singularize(field.getName());
								System.out.println(key+"数据类型是："+type);
								vBuffField.append(" and "+key+" in (");
								for(Integer vStr:vInts) {
									if (vStr!=null) {
										vBuffField.append(""+vStr+",");
									}
								}
								vBuffField.append(")");
							}else if (type.equals("list")) {
								//是数组集合类型的数据
								List vList=(List)value;
								String key=Inflector.getInstance().singularize(field.getName());
								System.out.println(key+"数据类型是："+type);
								vBuffField.append(" and "+key+" in (");
								for(Object vStr:vList) {
									if (vStr!=null) {
										vBuffField.append("'"+vStr+"',");
									}
								}
								vBuffField.append(")");
							} else {
								if (field.getName().endsWith("Contains")) {
							 
								// like
								String key = field.getName().replaceAll("Contains", "");
								if (checkFieldInMap(key, map)) {
									vBuffField.append(" and " + key + " like '%" + value + "%' ");
								}
							} else if (field.getName().endsWith("From")) {
								String key = field.getName().replaceAll("From", "");
								if (checkFieldInMap(key, map)) {
									vBuffField.append(" and " + key + " >= '" + value + "' ");
								}
							} else if (field.getName().endsWith("To")) {
								String key = field.getName().replaceAll("To", "");
								if (checkFieldInMap(key, map)) {
									vBuffField.append(" and " + key + " <= '" + value + "' ");
								}
							} else if (field.getName().endsWith("IsNull")) {
								String key = field.getName().replaceAll("IsNull", "");
								if (checkFieldInMap(key, map)) {
									if (value.toString().equals("true")) {
										vBuffField.append(" and " + key + " is null ");
									} else {
										vBuffField.append(" and " + key + " is not null ");
									}
								}
							} else if (field.getName().endsWith("NotEquals")) {
								String key = field.getName().replaceAll("NotEquals", "");
								if (checkFieldInMap(key, map)) {
									vBuffField.append(" and " + key + " != '" + value + "' ");
								}
							} else {
								// 要判断该字段名是否存在与bean类
								if (checkFieldInMap(field.getName(), map)) {
									vBuffField.append(" and " + field.getName() + " = '" + value + "' ");
								}
							}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String whereField = vBuffField.toString();
		if (whereField != null && !whereField.trim().equals("") && !whereField.trim().equals("and")) {

			vBuff.append(whereField);
		}
		return sqlReplace(vBuff.toString());
	}

	/**
	 * 替换不规则字符
	 * @param sql
	 * @return
	 */
	public static String sqlReplace(String sql) {
		return sql.replace(", from ", " from ").replace(", where ", " where ").replace(",where ", " where ").replace("1=0", "1=1").replace(",)", ")");
	}
	/**
	 * 递归获取该类的所有属性，包含其父类属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Field> getClassAllField(Class clazz) {
		List<Field> fieldList = new ArrayList<>();
		Class tempClass = clazz;
		while (tempClass != null) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}
		return fieldList;
	}

	/***
	 * 判断key是否在map中，如果存在，则返回该字段key对应的类型，string，integer之类的
	 * 
	 * @param key
	 * @param map
	 * @return
	 */
	public static boolean checkFieldInMap(String key, Map<String, String> map) {
		return map.containsKey(key);
	}
}
