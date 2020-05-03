package com.lsm.advancedandroid.learningprogram.aidl_demo.aidl;

/**
 * @NAME: Registry
 * @Des:  IPCFrame
 * @USER: shiming
 * @DATE: 2020/5/3 15:24
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
public class Registry {

    private static Registry instance;

    private Registry(){

    }

    public static Registry getInstance() {
        if (instance==null){
            synchronized (Registry.class){
                if (instance==null){
                    instance =new Registry();
                }
            }
        }
        return null;
    }

    /**
     * 做两张表 服务表class 标记
     * @param clazz
     */
    public void register(Class<?> clazz) {

        clazz.getAnnotation()
    }
}
