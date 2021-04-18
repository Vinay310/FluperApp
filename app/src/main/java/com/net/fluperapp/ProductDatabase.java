package com.net.fluperapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.net.fluperapp.model.Converter;
import com.net.fluperapp.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance;
    private static Context activity;

    public abstract ProductDao productDao();

    public static synchronized ProductDatabase getInstance(Context context) {
        activity = context.getApplicationContext();
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProductDatabase.class, "product_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private final static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAyncTask(instance).execute();


        }
    };

    private static class PopulateDbAyncTask extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao;

        private PopulateDbAyncTask(ProductDatabase db) {
            productDao = db.productDao();
            new PopulateDbAyncTask(instance).execute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fillJSONData(activity);
            return null;
        }
    }

    private static void fillJSONData(Context context) {
        ProductDao productDao = getInstance(context).productDao();
        JSONArray products = loadJsonArray(context);
        Log.e("product array is", "is " + products);
        try {
            for (int i = 0; i < products.length(); i++) {
                JSONObject jsonObject = products.getJSONObject(i);
                ArrayList<String> colors = new ArrayList<>();
                colors.add(jsonObject.getJSONArray("colors").getJSONObject(i).getString(""));
                ArrayList<String> stores = new ArrayList<>();
                colors.add(jsonObject.getJSONArray("stores").getJSONObject(i).getString(""));
                productDao.insert(new Product(
                        jsonObject.getString("name"),
                        jsonObject.getString("description"),
                        jsonObject.getString("regular price"),
                        jsonObject.getString("sale price"),
                        jsonObject.getString("product photo"),
                        colors,
                        stores


                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONArray loadJsonArray(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.product);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            return jsonObject.getJSONArray("products");
        } catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }
        return null;

    }


}
