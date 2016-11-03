package com.atguigu.beijingnews1011.utils;


import android.content.Context;

/**
 * 作者：苗振忠 on 2016/10/13 12:17
 * QQ号：1635235014
 * 邮箱：miaozhenzhong@sina.com
 * 作用: android dp和px之间转换 (屏幕适配 小红点问题)
 */

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
