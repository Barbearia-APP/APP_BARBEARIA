package io.osvaldocabral.appbarbearia;

import java.util.ArrayList;

public class DataSingleton {


    public ArrayList<Establishment> listEstablishment = new ArrayList<>();


    public DataSingleton() {
        listEstablishment.add(new Establishment("NOSTRINKS CABELEREIRO", "Rua do Barbeiro, 1345", "(41) 9 8888-8888", "/storage/emulated/0/Android/data/io.osvaldocabral.validadordepresenca/files/Pictures/pic_202106132046382536392854497873818.jpg"));
    }


    public static DataSingleton instance = new DataSingleton();


    public static DataSingleton getInstance() {
        return instance;
    }

}
