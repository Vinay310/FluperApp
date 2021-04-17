package com.net.fluperapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.net.fluperapp.databinding.ProductItemBinding;
import com.net.fluperapp.model.Product;
import com.net.fluperapp.model.ProductSelectListener;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Product> products = new ArrayList<>();
    ProductSelectListener productSelectListener;

    public ProductAdapter(Context context,ProductSelectListener productSelectListener) {
        this.context = context;
        this.productSelectListener=productSelectListener;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductItemBinding productItemBinding;

        public ProductViewHolder(@NonNull ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }

        public void bind(Product product) {
            productItemBinding.setVariable(BR.product, product);
            productItemBinding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding productItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item, parent, false);
        return new ProductViewHolder(productItemBinding);
    }

    public void setProducts(List<Product> productList) {
        this.products = productList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
        holder.itemView.setOnClickListener(v -> {
           productSelectListener.onProductSelect(product);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
