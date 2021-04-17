package com.net.fluperapp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Map;

@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "regular price")
    public String regular_price;
    @ColumnInfo(name = "sale price")
    public String sale_price;
    @ColumnInfo(name = "product photo")
    public String product_photo;
    @ColumnInfo(name = "colors")
    public ArrayList<String> colors;
    @ColumnInfo(name = "stores")
    public ArrayList<String> stores;

    public Product(String name, String description, String regular_price, String sale_price, String product_photo, ArrayList<String> colors, ArrayList<String> stores) {
        this.name = name;
        this.description = description;
        this.regular_price = regular_price;
        this.sale_price = sale_price;
        this.product_photo = product_photo;
        this.colors = colors;
        this.stores = stores;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public String getSale_price() {
        return sale_price;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public ArrayList<String> getColors() {
        return colors;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public ArrayList<String> getStores() {
        return stores;
    }
    @BindingAdapter("product_image")
    public static void loadTypeImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}

