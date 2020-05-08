package com.lsm.advancedandroid.learningprogram.cache;

/**
 * @author : ShiMing
 * @editor :
 * @description :
 * @created : 2020-05-05 13:54
 */
public interface ICache {

    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

    boolean contains(String key);

    void clear();

}
