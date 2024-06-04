package com.example.MealMinder.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MealMinder.FileUtil;
import com.example.MealMinder.R;
import com.example.MealMinder.model.MealData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class RekapAdaper extends RecyclerView.Adapter<RekapAdaper.ViewHolder> {
    private final ArrayList<HashMap<String,Object>> DataSet;
    private Context mContext;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView day,date,month,deskripsi,judul,time;
        public ViewHolder(View view) {
            super(view);
            day = view.findViewById(R.id.day);
            date = view.findViewById(R.id.date);
            month = view.findViewById(R.id.month);
            deskripsi = view.findViewById(R.id.deskripsi);
            judul = view.findViewById(R.id.judul);
            time = view.findViewById(R.id.time);
        }


        public TextView getDate() {
            return date;
        }

        public TextView getDay() {
            return day;
        }

        public TextView getDeskripsi() {
            return deskripsi;
        }

        public TextView getJudul() {
            return judul;
        }

        public TextView getMonth() {
            return month;
        }

        public TextView getTime() {
            return time;
        }
    }

    public RekapAdaper(Context context, ArrayList<HashMap<String,Object>> dataSet) {
        DataSet = dataSet;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_riwayat,viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        viewHolder.getDay().setText(Objects.requireNonNull(DataSet.get(position).get("hari")).toString());
        viewHolder.getDate().setText(Objects.requireNonNull(DataSet.get(position).get("tanggal")).toString());
        viewHolder.getMonth().setText(Objects.requireNonNull(DataSet.get(position).get("bulan")).toString());
        viewHolder.getJudul().setText(Objects.requireNonNull(DataSet.get(position).get("judul")).toString());
        viewHolder.getDeskripsi().setText(Objects.requireNonNull(DataSet.get(position).get("deskripsi")).toString());
        viewHolder.getTime().setText(Objects.requireNonNull(DataSet.get(position).get("waktu")).toString());
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }
}


