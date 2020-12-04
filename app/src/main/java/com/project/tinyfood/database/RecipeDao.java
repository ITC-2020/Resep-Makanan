package com.project.tinyfood.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.project.tinyfood.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT*FROM recipes")
    List<Recipe> getAllData();

    @Insert
    void insertData(Recipe recipe);

    @Update
    void updateData(Recipe recipe);

    @Delete
    void deleteData(Recipe recipe);
}
