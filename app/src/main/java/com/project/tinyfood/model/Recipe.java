package com.project.tinyfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;
    @ColumnInfo(name = "Title")
    private String title;
    @ColumnInfo(name = "Ingredient")
    private String ingredient;
    @ColumnInfo(name = "Instruction")
    private String instruction;
    @ColumnInfo(name = "Note")
    private String note;

    @Ignore
    public Recipe(int id, String title, String ingredient, String instruction, String note) {
        this.id = id;
        this.title = title;
        this.ingredient = ingredient;
        this.instruction = instruction;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public Recipe(String title, String ingredient, String instruction, String note) {
        this.title = title;
        this.ingredient = ingredient;
        this.instruction = instruction;
        this.note = note;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        title = in.readString();
        ingredient = in.readString();
        instruction = in.readString();
        note = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(ingredient);
        parcel.writeString(instruction);
        parcel.writeString(note);
    }
}
