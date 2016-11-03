package com.atguigu.beijingnews1011.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：苗振忠 on 2016/10/13 16:29
 * QQ号：1635235014
 * 邮箱：miaozhenzhong@sina.com
 * 作用: 一个基类（左侧菜单和正文Fragment的基类）
 */
public abstract class BaseFragment extends Fragment{

    /**
     * mActivity相当于上下文
     */
    public Activity mActivity;

    /**
     * 当BaseFragment被创建的时候回调这个方法
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    /**
     * 当Fragment作为视图被创建的时候回调
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }


    /**
     * 强制孩子实现自己的视图
     * @return
     */
    public abstract View initView();


    /**
     * 当Activity被创建的时候回调这个方法   通常请求网络数据
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(); //当activity被创建的时候回调
    }

    /**
     * 当子类需要联网请求数据的时候，重写方法即可（虽然该方法是空的，也不能少）
     */
    public void initData() {

    }
}