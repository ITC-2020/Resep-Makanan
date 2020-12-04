package com.project.tinyfood.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.project.tinyfood.model.Recipe;

@Database(entities = Recipe.class, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    private static final String DB_NAME = "db_recipes";
    private static RecipeDatabase instance;
    public abstract RecipeDao recipeDao();

    public static RecipeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RecipeDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}