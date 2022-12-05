package com.example.rabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.media.MediaPlayer;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.moneymoneymoney);
        mediaPlayer.start();
        imageView=findViewById(R.id.myrabbit1);
        imageView2=findViewById(R.id.myrabbit2);
        imageView3=findViewById(R.id.myrabbit3);
        imageView4=findViewById(R.id.myrabbit4);
        imageView5=findViewById(R.id.myrabbit5);

        setTranslateAnimation(imageView);
        setTranslateAnimation(imageView2);
        setTranslateAnimation(imageView3);
        setTranslateAnimation(imageView4);
        setTranslateAnimation(imageView5);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void startGame(View view){
//        mediaPlayer.pause();
//        GameView gameView = new GameView(this);
//        setContentView(gameView);
        Intent intent=new Intent(MainActivity.this,ChoiceMusicActivity.class);
        startActivity(intent);
    }
    //图片上下浮动
    private void setTranslateAnimation(ImageView iv_chat_head) {
        ValueAnimator animator = ValueAnimator.ofInt(0, -25, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (Integer) animation.getAnimatedValue();
                // 获得改变后的值

                System.out.println(currentValue);
                // 输出改变后的值

                iv_chat_head.setTranslationY(currentValue);

                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                iv_chat_head.requestLayout();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(500);
        animator.start();
    }
}