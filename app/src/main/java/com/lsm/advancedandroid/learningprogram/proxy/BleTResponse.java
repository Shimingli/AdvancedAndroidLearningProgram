package com.lsm.advancedandroid.learningprogram.proxy;


public interface BleTResponse<T> {
    void onResponse(int code, T data);
}
