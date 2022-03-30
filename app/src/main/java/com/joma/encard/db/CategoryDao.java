package com.joma.encard.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.joma.encard.data.model.roomModel.CategoryModel;
import com.joma.encard.data.model.roomModel.WordModel;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categorymodel")
    LiveData<List<CategoryModel>> getCategory();

    @Insert
    void addCategory(CategoryModel model);

    @Delete
    void delete(CategoryModel model);
}