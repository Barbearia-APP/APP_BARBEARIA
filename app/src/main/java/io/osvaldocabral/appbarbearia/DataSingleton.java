package io.osvaldocabral.appbarbearia;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSingleton {


    public ArrayList<Establishment> listEstablishment = new ArrayList<>();
    public FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    public Task<QuerySnapshot> taskFirestore;


    public DataSingleton() {
        taskFirestore = firestore.collection("establishments").get();
    }


    public static DataSingleton instance = new DataSingleton();


    public static DataSingleton getInstance() {
        return instance;
    }


}
