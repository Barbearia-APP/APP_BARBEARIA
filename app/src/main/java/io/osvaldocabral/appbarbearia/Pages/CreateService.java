package io.osvaldocabral.appbarbearia.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.Model.Establishment;
import io.osvaldocabral.appbarbearia.Model.ServiceBarber;
import io.osvaldocabral.appbarbearia.R;

public class CreateService extends AppCompatActivity {

    TextView nameService;
    TextView valueService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);
        nameService = findViewById(R.id.InputNameService);
        valueService = findViewById(R.id.InputValueService);

    }

    public void CreateServiceValues(View view){
        String nameServi = nameService.getText().toString();
        String valueServi = valueService.getText().toString();
        ServiceBarber serviceBarber = new ServiceBarber(nameServi,Float.parseFloat(valueServi),DataSingleton.getInstance().user.getUid());
        Map<String, Object> data = new HashMap<>();
        data.put("name",serviceBarber.getName());
        data.put("value",serviceBarber.getValue());
        data.put("uuid",serviceBarber.getUuid());
        FirebaseFirestore db = DataSingleton.getInstance().firestore;
        db.collection("ServiceBarber").document(serviceBarber.getId_user())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateService.this);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateService.this);
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

