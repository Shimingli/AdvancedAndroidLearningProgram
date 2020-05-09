package com.lsm.advancedandroid.aidlframe.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @NAME: Test
 * @Des:
 * @USER: shiming
 * @DATE: 2020/5/9
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
@Retention(RetentionPolicy.RUNTIME) //给反射用
@Target(ElementType.TYPE)
public @interface ServiceId {
    String value();
}
