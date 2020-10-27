package com.rcc.datefromnetwork1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rcc.datefromnetwork1.R;

public class spalsh extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        imageView=findViewById(R.id.imageView2);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim);
        imageView.setAnimation(animation);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(spalsh.this,startactiv.class));
            }
        },3000);


    }
}