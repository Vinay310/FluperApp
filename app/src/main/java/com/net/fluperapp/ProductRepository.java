package com.net.fluperapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.net.fluperapp.model.Product;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ProductRepository(Application application) {
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        productDao=productDatabase.productDao();
        allProducts = productDao.getAllProducts();
    }

    public void insert(Product product) {
        productDao.insert(product);
    }

    public void delete(Product product) {
        productDao.delete(product);
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
}
