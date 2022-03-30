package com.joma.encard.data.model.roomModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordModel {

    @PrimaryKey(autoGenerate = true)
    private int key;
    private String imgUrl;
    private String word;
    private String category;

    public WordModel(String imgUrl, String word, String category) {
        this.imgUrl = imgUrl;
        this.word = word;
        this.category = category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
