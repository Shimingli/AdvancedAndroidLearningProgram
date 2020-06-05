package com.lsm.advancedandroid.learningprogram.rxjavademo.use.api;


import com.lsm.advancedandroid.learningprogram.rxjavademo.use.bean.ProjectBean;
import com.lsm.advancedandroid.learningprogram.rxjavademo.use.bean.ProjectItem;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WangAndroidApi {

    // 总数据
    @GET("project/tree/json")
    Observable<ProjectBean> getProject();  // 异步线程 耗时操作

    // ITem数据
    @GET("project/list/{pageIndex}/json") // ?cid=294
    Observable<ProjectItem> getProjectItem(@Path("pageIndex") int pageIndex, @Query("cid") int cid);  // 异步线程 耗时操作
}
