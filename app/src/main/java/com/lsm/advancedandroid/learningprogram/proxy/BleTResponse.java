package com.lsm.advancedandroid.learningprogram.proxy;

/**
 * Created by dingjikerbo on 2016/8/28.
 */
public interface BleTResponse<T> {
    void onResponse(int code, T data);
}
