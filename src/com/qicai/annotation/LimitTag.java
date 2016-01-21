package com.qicai.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * È¨ÏÞÀ¹½Ø£¬
 * µÇÂ¼À¹½Ø ÒÔ¼°uriÀ¹½Ø
 * @author qzm
 *
 * @since 2015-9-2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitTag {
	boolean adminLogin() default true;//µÇÂ¼À¹½Ø
	/**
	 * À¹½Øuri
	 * @return
	 */
	boolean uri() default false;//ÐèÒª¸ù¾Ý²Ëµ¥À¹½Ø
}
