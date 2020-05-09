package com.lsm.advancedandroid.learningprogram.aidl_demo.demo.location;

import android.location.Location;

import com.lsm.advancedandroid.aidlframe.annotation.ServiceId;


@ServiceId("LocationManager")
public interface ILocationManager {
    Location getLocation();
}
