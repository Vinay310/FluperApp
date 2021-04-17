package com.net.fluperapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.net.fluperapp.ProductAdapter;
import com.net.fluperapp.R;
import com.net.fluperapp.databinding.ActivityProductListBinding;
import com.net.fluperapp.model.Product;
import com.net.fluperapp.model.ProductSelectListener;
import com.net.fluperapp.model.ProductViewModel;

import java.util.List;

public class ProductListActivity extends AppCompatActivity implements ProductSelectListener {
    ActivityProductListBinding activityProductListBinding;
    ProductViewModel productViewModel;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProductListBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_list);
        productViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(ProductViewModel.class);
        productAdapter = new ProductAdapter(this, ProductListActivity.this);
        activityProductListBinding.setProductAdapter(productAdapter);
        productViewModel.getAllproducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productAdapter.setProducts(products);
            }
        });

    }


    @Override
    public void onProductSelect(Product product) {
        int product_id = product.getId();
        Intent intent = new Intent(this, ShowProductActivity.class);
        intent.putExtra("product_id", product_id);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("product_des", product.getDescription());
        intent.putExtra("product_regular_price", product.getRegular_price());
        intent.putExtra("product_sale_price", product.getSale_price());
        intent.putExtra("product_image", product.getProduct_photo());
        startActivity(intent);
    }
}