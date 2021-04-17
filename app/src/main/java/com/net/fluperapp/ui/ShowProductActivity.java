package com.net.fluperapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.net.fluperapp.R;
import com.net.fluperapp.databinding.ActivityShowProductBinding;
import com.net.fluperapp.model.ProductViewModel;

public class ShowProductActivity extends AppCompatActivity {
    ActivityShowProductBinding activityShowProductBinding;
    ProductViewModel productViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityShowProductBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_product);
        if (getIntent().getIntExtra("product_id", 0) != 0) {
            activityShowProductBinding.tvProductName.setText(getIntent().getStringExtra("product_name"));
            activityShowProductBinding.tvDescription.setText(getIntent().getStringExtra("product_des"));
            activityShowProductBinding.tvRegularPrice.setText(getIntent().getStringExtra("product_regular_price"));
            activityShowProductBinding.tvSellPrice.setText(getIntent().getStringExtra("product_sale_price"));
            Glide.with(this).load(getIntent().getStringExtra("product_image")).into(activityShowProductBinding.ivProductImage);
        }
        productViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(ProductViewModel.class);
        activityShowProductBinding.ivProductImage.setOnClickListener(v -> {
            Intent intent = new Intent(this, FullScreenImage.class);
            intent.putExtra("product_image", getIntent().getStringExtra("product_image"));
            startActivity(intent);
        });
//        activityShowProductBinding.deleteProduct.setOnClickListener(v->{
//
//        });
    }
}