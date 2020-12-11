package com.project.tinyfood;


import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Data {

    HashMap<String, String> data = new HashMap<>();
    datadummy datadummy1 = new datadummy();
    int dataImg;

    public Data(HashMap<String, List<String>> data, int dataImg, int position){
        for (short i= 0; i < datadummy1.dataName.length; i++)
            this.data.put(datadummy1.dataName[i], Objects.requireNonNull(data.get(datadummy1.dataName[i])).get(position));
        this.dataImg = dataImg;
    }

    public String getTitle(){return  this.data.get(datadummy1.dataName[0]);}
    public String getCategory(){return  this.data.get(datadummy1.dataName[1]);}
    public String getResep(){return  this.data.get(datadummy1.dataName[2]);}
    public int getDataImg(){ return this.dataImg;}


}
