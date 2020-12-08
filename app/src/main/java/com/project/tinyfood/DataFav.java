package com.project.tinyfood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataFav {
    public static ArrayList<ArrayList<String>> data = new ArrayList<>(
            Arrays.asList(new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()));
    public static ArrayList<Integer> imgData = new ArrayList<>();
    HashMap<String, List<String>> content = new HashMap<>();
    String[] dataName = {"nama", "kategori", "resep"};
    DataFav()
    {
        for(char i = 0; i < dataName.length; i++)
            content.put(dataName[i], new ArrayList<>(data.get(i)));
    }

    public void getData(String[] data, int img) {
        for(char i = 0; i < data.length; i++)
            DataFav.data.get(i).add(data[i]);
        imgData.add(img);
    }

    void removeData(String dataNama)
    {
        for(short i = 0; i < data.get(0).size(); i++)
            if(dataNama.equals(data.get(0).get(i))){
                for(char j = 0; j < dataName.length; j++)
                    data.get(j).remove(i);
                imgData.remove(i);
            }
    }


    public ArrayList<Data> getAllFav() {
        ArrayList<Data> list = new ArrayList<>();
        for (short i = 0; i < content.get(dataName[0]).size(); i++) {
            Data data = new Data(content, imgData.get(i), i);
            list.add(data);
        }
        return list;
    }
}
