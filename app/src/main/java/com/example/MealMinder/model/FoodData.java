package com.example.MealMinder.model;

public class FoodData {

    String foodName;
    String descp;
    Integer imageUrl;

    public FoodData(String foodName, String descp, Integer imageUrl) {
        this.foodName = foodName;
        this.descp = descp;
        this.imageUrl = imageUrl;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
