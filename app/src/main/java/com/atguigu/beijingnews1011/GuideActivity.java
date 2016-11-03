package com.atguigu.beijingnews1011;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.atguigu.beijingnews1011.utils.CacheUtils;
import com.atguigu.beijingnews1011.utils.DensityUtil;

import java.util.ArrayList;

/**
 * 作者：苗振忠 on 2016/10/11 18:04
 * QQ号:1635235014
 * 邮箱：miaozhenzhong@sina.com
 * 作用:引导界面
 */
public class GuideActivity extends Activity{

    private static final String TAG = GuideActivity.class.getSimpleName();//好处：类改名字，这也跟着改

    private ViewPager viewpager_guide;//声明 viewpager
    private Button btn_start_main;    //声明 开始体验按钮
    private LinearLayout ll_point_group;  //三个小灰点活动区域
    private ImageView iv_red_point;   //声明动态的小红点
    private ArrayList<ImageView> imageViews;   //引导页面集合

    private int[] ids = {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

    //间距的话，我们写的：类的成员变量
    private int margLeft = 0;

    //DensityUtil创建类的成员变量（小红点）
    private int screenPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        /**
         * 使用ViewPager
         * ①在布局中定义ViewPager
         * ②在代码中实例化
         * ③准备数据(网络的或者本地的)----封装成集合
         * ④设置适配器pagerAdapter
         */

        //③准备数据(网络的或者本地的)----封装成集合
        int[] ids = {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
        imageViews = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);//要设置背景，填充

            //把图片加入到集合中
            imageViews.add(imageView);
        }

        //④设置适配器pagerAdapter
        viewpager_guide.setAdapter(new MyPagerAdapter());


        //DensityUtil 小红点屏幕适配 [把10当成dp，在不同的手机上转换成不同的值]
        screenPoint = DensityUtil.dip2px(this, 10);
        Log.e(TAG, "screenPoint == " + screenPoint);

        //小灰点(有多少个页面创建多少个点)---for循环
        for (int i = 0;i<ids.length;i++){
            ImageView norma_point = new ImageView(this);
            norma_point.setBackgroundResource(R.drawable.norma_point);

            if(i != 0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenPoint,screenPoint);
                params.leftMargin = screenPoint;
                norma_point.setLayoutParams(params);
            }
            ll_point_group.addView(norma_point);
        }
        //计算小红点间距：margLeft = 第一个点的距离-第零个点的距离
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyViewTreeObserver());


        //监听ViewPager页面的改变
        viewpager_guide.addOnPageChangeListener(new MyOnPageChangeListener());

        //监听btn_start_main点击事件监听
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录一下是否是进入过主页面
                CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //写OnPageChangeListener接口
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         *
         * @param position  当前页面的位置
         * @param positionOffset   在屏幕上移动的百分比
         * @param positionOffsetPixels   屏幕上移动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            Log.e(TAG, "position == "+ position + ",positionOffset == " + positionOffset + ",positionOffsetPixels == " + positionOffsetPixels);

            //点滑动的距离  = 间距 * 移动屏幕的百分比
            float leftMarg = margLeft * position + margLeft * positionOffset;

            //点移动的坐标 = 原来的左边 + 点滑动的距离  (设置红点移动的参数)
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(screenPoint,screenPoint);
            params.leftMargin = (int) leftMarg;
            iv_red_point.setLayoutParams(params);

        }

        /**
         *
         * @param position  某个页面被选中
         */
        @Override
        public void onPageSelected(int position) {
            
            //滑动到最后一个引导页面,按钮显示出来
            if(position == ids.length-1) {

                //1.如果是最后一个引导页面 显示按钮
                btn_start_main.setVisibility(View.VISIBLE);

            }else {

                //2.如果不是最后一个页面  隐藏按钮
                btn_start_main.setVisibility(View.GONE);
            }

        }

        /**
         *三种状态切换的时候回调  SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
         * @param state   页面滑动状态的改变
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //写OnGlobalLayoutListener接口
    class MyViewTreeObserver implements ViewTreeObserver.OnGlobalLayoutListener{
        @Override
        public void onGlobalLayout() {
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            //计算小红点间距：margleft = 第一个点的距离-第零个点的距离
            margLeft = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
            Log.e(TAG, "margLeft == "+margLeft);
        }
    }

    //写设PagerAdapter接口
    class MyPagerAdapter extends PagerAdapter{
        /**
         * 1.返回的条数
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 2.初始化每个页面
         * @param container  相当于：viewpager容器
         * @param position   页面位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        /**
         * 3.当前视图
         * @param view 当前视图
         * @param object instantiateItem返回的view
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 4.销毁当前页面
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        viewpager_guide = (ViewPager)findViewById(R.id.viewpager_guide);
        btn_start_main = (Button)findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout)findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView)findViewById(R.id.iv_red_point);
    }
}
