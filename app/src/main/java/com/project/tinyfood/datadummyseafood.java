package com.project.tinyfood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class datadummyseafood {

    String [][] data = {
            {"Kerang Rebus"},
            {"seafood" },
            {"langkah seafood" }
    };

    int[] imgFood = {

            R.drawable.kerang,

    };

    String[] dataName = {"nama" , "kategori" , "resep"};


    HashMap<String, List<String>> content = new HashMap<>();

    datadummyseafood()
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
