package com.lsm.advancedandroid.learningprogram.aidl_demo.aidl;

/**
 * @NAME: IPC
 * @Des:
 * @USER: shiming
 * @DATE: 2020/5/3 15:23
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
public class IPC {
    //注册
    public static void register(Class<?> service){
        Registry.getInstance().register(service);
    }
}
