package com.atguigu.beijingnews1011;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.atguigu.beijingnews1011.fragment.ContentFragment;
import com.atguigu.beijingnews1011.fragment.LeftMenuFragment;
import com.atguigu.beijingnews1011.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * 作者：苗振忠 on 2016/10/13 13:08
 * QQ号：1635235014
 * 邮箱：miaozhenzhong@sina.com
 * 作用: 主页面
 */
public class MainActivity extends SlidingFragmentActivity{

    public static final String LEFTMENU_TAG = "leftmenu_tag";
    public static final String MAIN_TAG = "main_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.设置主页
        setContentView(R.layout.activity_main);

        //2.设置左侧菜单的页面
        setBehindContentView(R.layout.leftmenu);

        //3.设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.rightmenu);

        //4.设置模式：左侧+主页；左侧+主页+右侧；主页+右侧
        slidingMenu.setMode(SlidingMenu.LEFT);

        //5.设置滑动的区域 ：全屏滑动、边缘滑动、不可以滑动
        slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);

        //6.设置主页面占200dp位置
        slidingMenu.setBehindOffset(DensityUtil.dip2px(this,200));

        initFragment();
    }


    private void initFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_leftmenu,new LeftMenuFragment(), LEFTMENU_TAG);
        ft.replace(R.id.fl_main,new ContentFragment(), MAIN_TAG);
        ft.commit();
    }
}
