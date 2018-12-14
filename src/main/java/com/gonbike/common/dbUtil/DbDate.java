package com.gonbike.common.dbUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * 此注解主要用于bean与页面交互的场景，bean中属性值是日期格式。自动生成SQL时，翻译成对应的功能
 * @author Shuaige
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DbDate {
	public String format()default "";
}
