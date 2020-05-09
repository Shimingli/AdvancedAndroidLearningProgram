// IIPCService.aidl
package com.lsm.advancedandroid.aidlframe;

// Declare any non-default types here with import statements
import com.lsm.advancedandroid.aidlframe.model.Response;
import com.lsm.advancedandroid.aidlframe.model.Request;

interface IIPCService {
        Response send(in Request request);
}
