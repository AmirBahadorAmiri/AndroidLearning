package com.amirbahadoramiri.androidlearning.newmodels;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CompanyConverter {
    private static final Gson gson = new Gson();

    // تبدیل List<WarehouseItem> ⇄ String (برای فیلد warehouse در Company)
    @TypeConverter
    public static String fromWarehouseList(List<WarehouseItem> warehouse) {
        if (warehouse == null) return null;
        return gson.toJson(warehouse);
    }

    @TypeConverter
    public static List<WarehouseItem> toWarehouseList(String json) {
        if (json == null) return null;
        Type listType = new TypeToken<List<WarehouseItem>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    // تبدیل List<KalaItem> ⇄ String (برای فیلد kala در Company)
    @TypeConverter
    public static String fromKalaList(List<KalaItem> kala) {
        if (kala == null) return null;
        return gson.toJson(kala);
    }

    @TypeConverter
    public static List<KalaItem> toKalaList(String json) {
        if (json == null) return null;
        Type listType = new TypeToken<List<KalaItem>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    // تبدیل List<MojodiItem> ⇄ String (برای فیلد mojodi در WarehouseItem)
    @TypeConverter
    public static String fromMojodiList(List<MojodiItem> mojodi) {
        if (mojodi == null) return null;
        return gson.toJson(mojodi);
    }

    @TypeConverter
    public static List<MojodiItem> toMojodiList(String json) {
        if (json == null) return null;
        Type listType = new TypeToken<List<MojodiItem>>() {}.getType();
        return gson.fromJson(json, listType);
    }
}