package com.net.fluperapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.net.fluperapp.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    LiveData<List<Product>> getAllProducts();

}
