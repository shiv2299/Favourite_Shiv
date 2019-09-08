package com.example.shivam.favourite;

/**
 * Created by Shivam on 06-09-2019.
 */

public class DataModel {
    String item;
    int fav;

    public DataModel(String item, int fav) {
        this.item = item;
        this.fav=fav;
    }
    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
