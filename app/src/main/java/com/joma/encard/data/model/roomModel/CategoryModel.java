package com.joma.encard.data.model.roomModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryModel {

    @PrimaryKey(autoGenerate = true)
    private int key;
    private String category;

    public CategoryModel(String category) {
        this.category = category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
