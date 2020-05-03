package com.lsm.advancedandroid.learningprogram.aidl_demo;

/**
 * @NAME: DataManager
 * @Des:
 * @USER: shiming
 * @DATE: 2020/5/3 14:44
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
public class DataManager {

    private static DataManager manager;
    private String mMsg;
    private final static Object obj= new Object();

    public static DataManager getInstance(){
        if (manager==null){
            synchronized (obj){
                if (manager==null){
                    manager=new DataManager();
                }
            }
        }
        return manager;
    }
    public void setManagerData(String msg){
        mMsg = msg;
    }

    public String getManagerData(){
       return mMsg;
    }

}
