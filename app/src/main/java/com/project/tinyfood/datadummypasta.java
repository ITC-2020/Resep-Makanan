package com.project.tinyfood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class datadummypasta {

    String [][] data = {
            {"Mie ayam"},
            {"pasta"},
            {"langkah mie ayam"}
    };

    int[] imgFood = {
            R.drawable.mieayam,
    };

    String[] dataName = {"nama" , "kategori" , "resep"};


    HashMap<String, List<String>> content = new HashMap<>();

    datadummypasta()
    {
        for (short i=0;i <dataName.length;i++)
        {
            content.put(dataName[i], new ArrayList<String>());
            Collections.addAll(Objects.requireNonNull(content.get(dataName[i])), data[i]);
        }
    }


    public HashMap<String, List<String>> getData () {return content;}
    public int [] getImgFood() {return imgFood; }

    public ArrayList<Data> getAlldatadummy(){
        ArrayList<Data> list = new ArrayList<>();
        for(short i=0 ; i < this.data[0].length;i++)
        {
            Data data = new Data(getData(), getImgFood(),i);
            list.add(data);

        }
        return list;

    }

}
