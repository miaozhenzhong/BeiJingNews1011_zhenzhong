package com.atguigu.beijingnews1011.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.atguigu.beijingnews1011.GuideActivity;
import com.atguigu.beijingnews1011.SplashActivity;

/**
 * 作者：苗振忠 on 2016/10/13 14:05
 * QQ号：1635235014
 * 邮箱：miaozhenzhong@sina.com
 * 作用: 缓存工具类
 */
public class CacheUtils {

    /**
     * 保存数据
     * @param context  上下文
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("miaozhenzhong",context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 获取数据
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("miaozhenzhong",context.MODE_PRIVATE);

        return sp.getBoolean(key,false);
    }
}
