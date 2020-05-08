package com.lsm.advancedandroid.learningprogram.cache;

import android.text.TextUtils;

import androidx.collection.LruCache;

/**
 * @author : ShiMing
 * @editor :
 * @description :
 * @created : 2020-05-05 13:55
 */
public class MemoryCacheManager implements ICache {

    private LruCache<String, Object> cache;


    private static class SingletonHolder{
        private static MemoryCacheManager INSTANCE = new MemoryCacheManager();
    }

    public static MemoryCacheManager getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private MemoryCacheManager() {
        cache = new LruCache<String, Object>(5);
    }


    @Override
    public synchronized void put(String key, Object value) {
        if (TextUtils.isEmpty(key)) return;

        if (cache.get(key) != null) {
            cache.remove(key);
        }
        cache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    public synchronized <T> T get(String key, Class<T> clazz) {
        try {
            return (T) cache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(String key) {
        if (cache.get(key) != null) {
            cache.remove(key);
        }
    }

    @Override
    public boolean contains(String key) {
        return key!=null&&cache.get(key) != null;
    }

    @Override
    public void clear() {
        cache.evictAll();
    }
}
