package com.example.MealMinder.adapter;


import android.content.Context;
import android.util.Log;
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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final ArrayList<HashMap<String,Object>> DataSet;
    private Context mContext;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView day,date,month,deskripsi,judul,time,options,makanan;
        public ViewHolder(View view) {
            super(view);
            makanan = view.findViewById(R.id.makanan);
            day = view.findViewById(R.id.day);
            date = view.findViewById(R.id.date);
            month = view.findViewById(R.id.month);
            deskripsi = view.findViewById(R.id.deskripsi);
            judul = view.findViewById(R.id.judul);
            time = view.findViewById(R.id.time);
            options = view.findViewById(R.id.options);
        }

        public TextView getOptions() {
            return options;
        }

        public TextView getMakanan() {
            return makanan;
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

    public HomeAdapter(Context context, ArrayList<HashMap<String,Object>> dataSet) {
        DataSet = dataSet;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_add,viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d("dataSet"," == " + DataSet.get(position));

        if (DataSet.get(position).containsKey("hari")) {
            viewHolder.getDay().setText(Objects.requireNonNull(DataSet.get(position).get("hari")).toString());
            viewHolder.getDate().setText(Objects.requireNonNull(DataSet.get(position).get("tanggal")).toString());
            viewHolder.getMonth().setText(Objects.requireNonNull(DataSet.get(position).get("bulan")).toString());
            viewHolder.getTime().setText(Objects.requireNonNull(DataSet.get(position).get("waktu")).toString());
        }

        viewHolder.getJudul().setText(Objects.requireNonNull(DataSet.get(position).get("judul")).toString());
        viewHolder.getDeskripsi().setText(Objects.requireNonNull(DataSet.get(position).get("deskripsi")).toString());

        if (DataSet.get(position).containsKey("makanan")){
            viewHolder.getMakanan().setText(DataSet.get(position).get("makanan").toString());
        }
//        else {
//            viewHolder.getMakanan().setText("");
//        }

        viewHolder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(), viewHolder.options);
                //inflating menu from xml resource
                popup.inflate(R.menu.option_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.Done) {
                            HashMap<String, Object> data = new HashMap<>();
                            data.put("hari", DataSet.get(position).get("hari").toString());
                            data.put("tanggal", DataSet.get(position).get("tanggal").toString());
                            data.put("bulan", DataSet.get(position).get("bulan").toString());
                            data.put("judul", DataSet.get(position).get("judul").toString());
                            data.put("deskripsi", DataSet.get(position).get("deskripsi").toString());
                            data.put("waktu", DataSet.get(position).get("waktu").toString());
                            if (MealData.riwayat == null) {
                                MealData.riwayat = new ArrayList<>();
                            }
                            MealData.riwayat.add(data);
                            MealData.maps.remove(position);
                            String s = new Gson().toJson(MealData.riwayat);
                            String d = new Gson().toJson(MealData.maps);
                            FileUtil.writeFile(mContext.getFilesDir()+"/meal",d);
                            FileUtil.writeFile(mContext.getFilesDir()+"/riwayat",s);
                            notifyDataSetChanged();
                            Toast.makeText(view.getContext(), "berhasil",Toast.LENGTH_SHORT).show();
                        }
                        if (item.getItemId()==R.id.Delete) {
                            MealData.maps.remove(position);
                            String s = new Gson().toJson(MealData.maps);
                            notifyDataSetChanged();
                            FileUtil.writeFile(mContext.getFilesDir()+"/meal",s);
                        }
                        return true;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }
}


