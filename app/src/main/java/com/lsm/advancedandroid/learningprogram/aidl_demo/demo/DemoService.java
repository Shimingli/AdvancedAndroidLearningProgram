package com.lsm.advancedandroid.learningprogram.aidl_demo.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.lsm.advancedandroid.aidlframe.IPC;
import com.lsm.advancedandroid.learningprogram.aidl_demo.demo.location.Location;
import com.lsm.advancedandroid.learningprogram.aidl_demo.demo.location.LocationManager;




/**
 * 服务进程
 */
public class DemoService extends Service {
    public DemoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //模拟定位
        LocationManager.getDefault().setLocation(new Location("岳麓区天之道", 1.1d, 2.2d));

        //将数据携带者（LocationManager）注册到框架
        IPC.regist(LocationManager.class);
    }
}
