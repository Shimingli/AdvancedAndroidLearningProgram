package com.lsm.advancedandroid.learningprogram.proxy.demo.api;


import com.lsm.advancedandroid.learningprogram.proxy.demo.retrofit.annotation.Field;
import com.lsm.advancedandroid.learningprogram.proxy.demo.retrofit.annotation.GET;
import com.lsm.advancedandroid.learningprogram.proxy.demo.retrofit.annotation.POST;
import com.lsm.advancedandroid.learningprogram.proxy.demo.retrofit.annotation.Query;

import okhttp3.Call;

public interface EnjoyWeatherApi {

    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);


    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);
}
