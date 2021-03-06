package com.net.fluperapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.net.fluperapp.R;
import com.net.fluperapp.databinding.ActivityMainBinding;
import com.net.fluperapp.model.Product;
import com.net.fluperapp.model.ProductViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProductViewModel productViewModel;
    ActivityMainBinding activityMainBinding;
    ArrayList<String> colors = new ArrayList<>();
    ArrayList<String> stores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);


    }

    public void createProduct(View view) {
        colors.add("swd");
        stores.add("A");
        productViewModel.insert(new Product(
                "Tide Plus Extra Power (Jasmine & Rose) - 7 kg Detergent Powder - with Free Detergent Powder - 3 kg",
                "Helps remove dirt from even washed clothes to give you brilliant whiteness\n" +
                        "The enzyme formula in the washing powder helps in washing away the toughest stains on the clothes\n",
                "800",
                "719",
                "http://hrry.in/bhojanshala/app_files/supercat_images/dinner.png",
                colors,
                stores

        ));
    }

    public void showProduct(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }
}