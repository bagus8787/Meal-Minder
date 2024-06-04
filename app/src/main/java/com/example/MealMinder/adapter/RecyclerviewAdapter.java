package com.example.MealMinder.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MealMinder.FoodDetails;
import com.example.MealMinder.ItemAnimation;
import com.example.MealMinder.R;
import com.example.MealMinder.model.FoodData;
import com.example.MealMinder.model.MealData;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerviewHolder> {

    Context context;
    List<FoodData> foodDataList;
    List<FoodData> filteredFoodDataList;

    public RecyclerviewAdapter(Context context, List<FoodData> foodDataList) {
        this.context = context;
        this.foodDataList = foodDataList;
        this.filteredFoodDataList = foodDataList;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_item, parent, false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.foodName.setText(foodDataList.get(position).getFoodName());
        holder.foodDesc.setText(foodDataList.get(position).getDescp());
        holder.foodImage.setImageResource(foodDataList.get(position).getImageUrl());

        ItemAnimation.animateFadeIn(holder.itemView, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealData.currentMeal = foodDataList.get(position).getFoodName();
                ((Activity)context).finish();
            }
        });

        holder.foodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Makanan dipilih", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return filteredFoodDataList.size();
    }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder{


        CircleImageView foodImage;
        TextView foodName, foodDesc;

        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodDesc = itemView.findViewById(R.id.foodDesc);

        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String Key = charSequence.toString();
                if (Key.isEmpty()){
                    filteredFoodDataList = foodDataList;
                }
                else {

                    List<FoodData> lstFiltered = new ArrayList<>();
                    for (FoodData row: foodDataList){
                        if (row.getFoodName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredFoodDataList = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredFoodDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filteredFoodDataList = (List<FoodData>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }


}
