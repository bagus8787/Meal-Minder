package com.example.MealMinder.model;

import android.content.Context;

import com.example.MealMinder.FileUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MealData {

    public static String currentMeal;
    public MealData(){

    }
    public static ArrayList<HashMap<String,Object>> maps;
    public static ArrayList<HashMap<String,Object>> riwayat;


    public static ArrayList<HashMap<String, Object>> getMaps(Context c) {
        ArrayList<HashMap<String,Object>> s = new ArrayList<>();
        if(FileUtil.isExistFile(c.getFilesDir()+"/meal")){
            if(!FileUtil.readFile(c.getFilesDir() + "/meal").equals("{}")){
                s = new Gson().fromJson(FileUtil.readFile(c.getFilesDir()+"/meal"),new TypeToken<ArrayList<HashMap<String,Object>>>(){}.getType());
            }
            }
        maps = s;
        return s;
    }



    public static String getJudul() {
        return judul;
    }

    public static String getDeskripsi() {
        return deskripsi;
    }

    public static String getHari() {
        return hari;
    }

    public static String getTanggal() {
        return tanggal;
    }

    public static String getBulan() {
        return bulan;
    }

    public static String getWaktu() {
        return waktu;
    }

    private static String judul,deskripsi,hari,tanggal,bulan,waktu;

    public static void setMealData(
            Context context,
            String judul,
            String deskripsi,
            String hari,
            String tanggal,
            String bulan,
            String waktu
    ){
        HashMap<String, Object> map = new HashMap<>();
        map.put("judul",judul);
        map.put("deskripsi",deskripsi);
        map.put("hari",hari);
        map.put("tanggal",tanggal);
        map.put("bulan",bulan);
        map.put("waktu",waktu);
        map.put("makanan",currentMeal);

        maps = getMaps(context);
        maps.add(map);
        String s = new Gson().toJson(maps);
        FileUtil.writeFile(context.getFilesDir()+"/meal",s);
    }
}