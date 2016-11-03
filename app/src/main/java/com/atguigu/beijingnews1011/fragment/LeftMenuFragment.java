package com.atguigu.beijingnews1011.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.beijingnews1011.R;
import com.atguigu.beijingnews1011.base.BaseFragment;

/**
 * 作者：苗振忠 on 2016/10/14 22:33
 * QQ号：1635235014
 * 邮箱：miaozhenzhong@sina.com
 * 作用:XXX
 */
public class LeftMenuFragment extends BaseFragment {
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_leftmenu,null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        System.out.print("侧滑菜单数据初始化");
    }
}
