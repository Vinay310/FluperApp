package com.net.fluperapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.net.fluperapp.R;
import com.net.fluperapp.model.TouchImageView;

public class FullScreenImage extends AppCompatActivity {
    TouchImageView touchImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        touchImageView = findViewById(R.id.tiv_image);
        String product_image = getIntent().getStringExtra("product_image");
        Glide.with(this).load(product_image).into(touchImageView);
    }
}