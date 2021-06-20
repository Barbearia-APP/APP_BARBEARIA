package io.osvaldocabral.appbarbearia.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.Model.CreateServiceDatetime;
import io.osvaldocabral.appbarbearia.R;

public class CreateDateTime extends AppCompatActivity {

    TextView InputDateTime;
    TextView InputTime;
    Spinner spinnerServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_date_time);
        InputDateTime = findViewById(R.id.InputDateTime);
        InputTime = findViewById(R.id.InputTime);
        spinnerServices = findViewById(R.id.spinnerServices);

        ArrayList<String> arrayList = new ArrayList<>();
        FirebaseFirestore db = DataSingleton.getInstance().firestore;
        Query query = db.collection("ServiceBarber").whereEqualTo("id_user",DataSingleton.getInstance().user.getUid());
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("testando", document.getId() + " => " + document.getData().get("name"));
                                arrayList.add(document.getData().get("name").toString());
                            }
                        } else {
                            Log.d("testando", "Error getting documents: ", task.getException());
                        }
                    }
                });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        spinnerServices.setAdapter(arrayAdapter);


    }

    public void SaveDateTime(View view){
        String date = InputDateTime.getText().toString();
        String time = InputTime.getText().toString();
        String services = spinnerServices.getSelectedItem().toString();
        FirebaseFirestore db = DataSingleton.getInstance().firestore;
        CreateServiceDatetime model = new CreateServiceDatetime(date,time,services);
        Map<String, Object> data = new HashMap<>();
        data.put("date",model.getDate());
        data.put("time",model.getTime());
        data.put("id_user",DataSingleton.getInstance().user.getUid());
        db.collection("ServiceDateTime").document(model.getUui())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateDateTime.this);
                        builder.setTitle( "Salvo com sucesso." );
                        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        builder.show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateDateTime.this);
                        builder.setTitle( "Erro, tente novamente." );
                        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        builder.show();
                    }
                });


    }


}
