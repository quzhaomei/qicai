package com.qicai.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Ȩ�����أ�
 * �Ż���¼���� 
 * @author qzm
 *
 * @since 2015-9-2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoorTag {
	
}
