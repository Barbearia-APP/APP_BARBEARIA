package io.osvaldocabral.appbarbearia;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import io.osvaldocabral.appbarbearia.Model.Establishment;

public class DataSingleton {


    public ArrayList<Establishment> listEstablishment = new ArrayList<>();
    public FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    public Task<QuerySnapshot> taskFirestore = firestore.collection("establishments").get();
    public FirebaseUser user;
    public Establishment currentEstablishment;


    public DataSingleton() {
    }


    public static DataSingleton instance = new DataSingleton();


    public static DataSingleton getInstance() {
        return instance;
    }


}
