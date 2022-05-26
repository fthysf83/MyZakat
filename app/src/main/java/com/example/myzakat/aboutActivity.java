package com.example.myzakat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class aboutActivity extends AppCompatActivity {
    ImageView imageView;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imageView=findViewById(R.id.imageView);
        drawable=getResources().getDrawable(R.drawable.logo);
        imageView.setImageDrawable(drawable);

        imageView=findViewById(R.id.imageView2);
        drawable=getResources().getDrawable(R.drawable.huda);
        imageView.setImageDrawable(drawable);

        imageView=findViewById(R.id.imageView3);
        drawable=getResources().getDrawable(R.drawable.fatihah);
        imageView.setImageDrawable(drawable);


    }
}