package com.k2senterprise.toptal;

public class Calories {
    private int id;
    private String user_id;
    private String age;
    private String user_weight;
    private String name;
    private double price;
    private int weight;
    private int calories;
    private double fat;
    private double carbs;
    private double protein;
    private String time_consumed;
    private String date_consumed;
    private String type;
    private String favorite;
    private String procedence;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getUser_weight() { return user_weight; }
    public void setUser_weight(String user_weight) { this.user_weight = user_weight; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public double getFat() { return fat; }
    public void setFat(double fat) { this.fat = fat; }

    public double getCarbs() { return carbs; }
    public void setCarbs(double carbs) { this.carbs = carbs; }

    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }

    public String getTime_consumed() { return time_consumed; }
    public void setTime_consumed(String time_consumed) { this.time_consumed = time_consumed; }

    public String getDate_consumed() { return date_consumed; }
    public void setDate_consumed(String date_consumed) { this.date_consumed = date_consumed; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFavorite() { return favorite; }
    public void setFavorite(String favorite) { this.favorite = favorite; }

    public String getProcedence() { return procedence; }
    public void setProcedence(String procedence) { this.procedence = procedence; }

    @Override
    public String toString() {
        return super.toString();
    }
}
