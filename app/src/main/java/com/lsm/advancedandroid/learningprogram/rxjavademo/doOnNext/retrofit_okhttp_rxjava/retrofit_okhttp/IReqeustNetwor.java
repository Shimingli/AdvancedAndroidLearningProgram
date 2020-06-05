package com.lsm.advancedandroid.learningprogram.rxjavademo.doOnNext.retrofit_okhttp_rxjava.retrofit_okhttp;

import io.reactivex.Observable;
import retrofit2.http.Body;

// 请求接口 API
public interface IReqeustNetwor {

    // 请求注册 功能  todo 耗时操作 ---> OkHttp
    public io.reactivex.Observable<com.xiangxue.rxjavademo.doOnNext.retrofit_okhttp_rxjava.retrofit_okhttp.RegisterResponse> registerAction(@Body com.xiangxue.rxjavademo.doOnNext.retrofit_okhttp_rxjava.retrofit_okhttp.RegisterRequest registerRequest);

    // 请求登录 功能 todo 耗时操作 ---> OKHttp
    public Observable<com.xiangxue.rxjavademo.doOnNext.retrofit_okhttp_rxjava.retrofit_okhttp.LoginResponse> loginAction(@Body com.xiangxue.rxjavademo.doOnNext.retrofit_okhttp_rxjava.retrofit_okhttp.LoginReqeust loginRequest);

}
