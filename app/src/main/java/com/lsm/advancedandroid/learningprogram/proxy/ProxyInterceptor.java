package com.lsm.advancedandroid.learningprogram.proxy;

import java.lang.reflect.Method;

/**
 * Created by dingjikerbo on 2016/9/18.
 */
public interface ProxyInterceptor {
    boolean onIntercept(Object object, Method method, Object[] args);
}
