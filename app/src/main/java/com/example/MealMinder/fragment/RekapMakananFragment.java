package com.example.MealMinder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MealMinder.FileUtil;
import com.example.MealMinder.R;
import com.example.MealMinder.adapter.RekapAdaper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class RekapMakananFragment extends Fragment{
    private RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = getLayoutInflater().inflate(R.layout.rekapmakanan,container,false);
        rv = v.findViewById(R.id.recycler);
        return  v;
    }



    @Override
    public void onResume() {
        super.onResume();
        if(FileUtil.isExistFile(getContext().getFilesDir()+"/riwayat")){
            if(!FileUtil.readFile(getContext().getFilesDir() + "/riwayat").equals("{}")){
                ArrayList<HashMap<String,Object>> s = new Gson().fromJson(FileUtil.readFile(getContext().getFilesDir()+"/riwayat"),new TypeToken<ArrayList<HashMap<String,Object>>>(){}.getType());
                RekapAdaper adapter = new RekapAdaper(getContext(),s);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }
    }
}