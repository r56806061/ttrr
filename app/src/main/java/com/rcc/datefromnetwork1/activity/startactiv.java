package com.rcc.datefromnetwork1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.rcc.datefromnetwork1.R;

public class startactiv extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startactiv);
        imageView=findViewById(R.id.imageView4);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim2);
        imageView.setAnimation(animation);




    }


    public void intent(View view) {
        Intent open=new Intent(startactiv.this,MainActivity.class);
        startActivity(open);
    }
}