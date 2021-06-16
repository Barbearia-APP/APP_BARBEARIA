package io.osvaldocabral.appbarbearia;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DataSingleton {


    public ArrayList<Establishment> listEstablishment = new ArrayList<>();
    FirebaseFirestore firestore;


    public DataSingleton() {
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("establishments").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println(document);
                    }
                }
                else {
                    Log.w("DEURUIM", "Error getting documents.", task.getException());
                }
            }
        });
//        listEstablishment.add(new Establishment("NOSTRINKS CABELEREIRO", "Rua do Barbeiro, 1345", "(41) 9 8888-8888", "/storage/emulated/0/Android/data/io.osvaldocabral.validadordepresenca/files/Pictures/pic_202106132046382536392854497873818.jpg"));
    }


    public static DataSingleton instance = new DataSingleton();


    public static DataSingleton getInstance() {
        return instance;
    }


}
