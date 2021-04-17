package com.net.fluperapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.net.fluperapp.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;
    private LiveData<List<Product>> allproducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        allproducts = productRepository.getAllProducts();
    }

    public void insert(Product product) {
        productRepository.insert(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public LiveData<List<Product>> getAllproducts() {
        return allproducts;
    }
}
