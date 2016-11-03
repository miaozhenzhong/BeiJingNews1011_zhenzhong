package com.atguigu.beijingnews1011;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.atguigu.beijingnews1011.utils.CacheUtils;

/**
*作者:苗振忠
*时间:2016/10/11 16:24
*作用:欢迎界面
*/
public class SplashActivity extends Activity {

    public static final String START_MAIN = "start_main"; //是否进入过主页面（缓存时的key值）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //欢迎界面：动画效果。(旋转、缩放、渐变动画)
        AnimationView();

    }

/**
*作者:苗振忠
*时间:2016/10/11 17:42
*作用: [欢迎界面：动画效果。(旋转、缩放、渐变动画)]
*/
    private void AnimationView() {
        //1.三个动画：旋转动画、缩放动画、渐变动画
        //旋转动画
        RotateAnimation ra = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(1000);//设置动画滚动时间
        ra.setFillAfter(true);//默认播放到最后的状态
        //缩放动画
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(1000);//设置动画滚动时间
        ra.setFillAfter(true);//默认播放到最后的状态
        //缩放动画
        AlphaAnimation aa = new AlphaAnimation(0,360);
        ra.setDuration(1000);//设置动画滚动时间
        ra.setFillAfter(true);//默认播放到最后的状态

        //2.把动画放入到AnimationSet中
        AnimationSet set = new AnimationSet(false);//插入器(默认不用，就用我们指定的ra.setDuration(3000);)
        set.addAnimation(ra);
        set.addAnimation(sa);
        set.addAnimation(aa);

        //rootview.setAnimation();[整个欢迎界面当作视图]
        View view = findViewById(R.id.rl_splash_rootview);
        view.startAnimation(set);
        
        //3.监听动画播放完成
        set.setAnimationListener(new MyAnimationListener());
    }
    //3.监听动画播放完成
    class MyAnimationListener implements Animation.AnimationListener {
        /**
         * 当动画开始播放的时候回调
         * @param animation
         */
        @Override
        public void onAnimationStart(Animation animation) {
            Toast.makeText(SplashActivity.this, "动画开始播放", Toast.LENGTH_SHORT).show();
        }

        /**
         * 当动画播放完成的时候回调
         * @param animation
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            //获取记录的结果
            boolean isEnterMained = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
            //如果进入过返回一个true
            if(isEnterMained) {
                //曾经进入过主页面（再次开启欢迎界面时，直接进入主页面）
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);//开始动画
            }else{
                //没有进入过主页面（直接进入到引导页面）
                Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
                startActivity(intent);//开始动画
            }

//            Toast.makeText(SplashActivity.this, "动画播放完成", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
//            startActivity(intent);//开始动画
            finish();//关闭当前界面
        }

        /**
         * 当动画重复播放的时候回调
         * @param animation
         */
        @Override
        public void onAnimationRepeat(Animation animation) {
            Toast.makeText(SplashActivity.this, "动画重复播放", Toast.LENGTH_SHORT).show();
        }
    }
}
