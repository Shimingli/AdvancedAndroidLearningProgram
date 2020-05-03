package com.lsm.advancedandroid.learningprogram.aidl_demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

/**
 * @NAME: GPSService
 * @Des:
 * @USER: shiming
 * @DATE: 2020/5/3 14:42
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
public class GPSService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public String getMsg() throws RemoteException {
                return  DataManager.getInstance().getManagerData();
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.getInstance().setManagerData("我是跨进程的信息哦");
    }
}
