package com.lsm.advancedandroid.learningprogram.aidl_demo.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lsm.advancedandroid.aidlframe.IPC;
import com.lsm.advancedandroid.aidlframe.IPCService;
import com.lsm.advancedandroid.learningprogram.R;
import com.lsm.advancedandroid.learningprogram.aidl_demo.DataManager;
import com.lsm.advancedandroid.learningprogram.aidl_demo.GPSService;

/**
 * @NAME: AIDLDemoActivity
 * @Des:
 * @USER: shiming
 * @DATE: 2020/5/3 14:41
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
public class AIDLUseDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_i_d_l_demo);
        startService(new Intent(this, GPSService.class));

        showToast();
    }

    private void showToast() {
        String managerData = DataManager.getInstance().getManagerData();
        Toast.makeText(this, managerData, Toast.LENGTH_SHORT).toString();
    }

    /**
     * 连接到服务端
     *
     * @param view
     */
    public void conect(View view) {
        IPC.connect(this, "com.scy.component.test_service", IPCService.IPCService0.class);

    }

    /**
     * 服务端需要做的事情
     */
    public void serService() {
        startService(new Intent(this, DemoService.class));
        IPC.connect(this, IPCService.IPCService0.class);
    }


    public void showLocation(View view) {
        //代理对象
        //ILocationManager location = IPC.getInstanceWithName(IPCService.IPCService0.class, ILocationManager.class, "getDefault");

        //Toast.makeText(this, "当前位置:" + location.getLocation(), Toast.LENGTH_LONG).show();
    }
}
