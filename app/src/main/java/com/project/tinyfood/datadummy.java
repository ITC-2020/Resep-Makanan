package com.project.tinyfood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class datadummy {

    String [][] data = {
            {"Steak" , "Kerang Rebus" , "Roti Cane" , "Mie ayam"},
            {"meat" , "seafood" , "dessert" , "pasta"},
            {"langkah steak" , "langkah seafood" , "langkah roti cane" , "langkah mie ayam"}
    };

    int[] imgFood = {
        R.drawable.steak,
            R.drawable.kerang,
        R.drawable.roti,
            R.drawable.mieayam,
    };

    String[] dataName = {"nama" , "kategori" , "resep"};


    HashMap<String, List<String>> content = new HashMap<>();

    datadummy()
    {
        for (short i=0;i <dataName.length;i++)
        {
            content.put(dataName[i], new ArrayList<String>());
            Collections.addAll(Objects.requireNonNull(content.get(dataName[i])), data[i]);
        }
    }

    public HashMap<String, List<String>> getData () {return content;}

    public ArrayList<Data> getAlldatadummy(){
        ArrayList<Data> list = new ArrayList<>();
        for(short i=0 ; i < this.data[0].length;i++)
        {
            Data data = new Data(getData(), imgFood[i],i);
            list.add(data);

        }
        return list;

    }

}
