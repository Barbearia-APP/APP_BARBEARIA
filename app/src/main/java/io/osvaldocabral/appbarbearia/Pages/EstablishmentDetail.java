package io.osvaldocabral.appbarbearia.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;

import io.osvaldocabral.appbarbearia.Model.Establishment;
import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.R;


public class EstablishmentDetail extends AppCompatActivity {


    Button buttonSave;
    EditText editTextName, editTextAddress, editTextPhone;
    Establishment establishment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_detail);

        buttonSave = findViewById(R.id.buttonSave);
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);

        establishment = DataSingleton.getInstance().currentEstablishment;

        if(establishment != null) fillWindow();
    }


    public void saveEstablishmentClicked(View view) {
        String name = editTextName.getText().toString();
        String address = editTextAddress.getText().toString();
        String phone = editTextPhone.getText().toString();

        CollectionReference collection = DataSingleton.getInstance().firestore.collection("establishments");

        if(establishment.getId() == null) {
            establishment = new Establishment(name, address, phone, "");
            collection.add(establishment);
        }
        else {
            collection.document(establishment.getId()).update(filEstablishment());
        }

        Toast.makeText(this, "Ação realizada com sucesso!", Toast.LENGTH_LONG).show();
        exitView();
    }


    public void cancelEstablishmentClicked(View view) {
        exitView();
    }


    public void fillWindow() {
        editTextName.setText(establishment.getName());
        editTextAddress.setText(establishment.getAddress());
        editTextPhone.setText(establishment.getPhone());
    }


    public Map<String, Object> filEstablishment() {
        Map<String, Object> establishmentAux = new HashMap<>();
        establishmentAux.put("name", editTextName.getText().toString());
        establishmentAux.put("address", editTextAddress.getText().toString());
        establishmentAux.put("phone", editTextPhone.getText().toString());

        return establishmentAux;
    }


    public void exitView() {
        DataSingleton.getInstance().currentEstablishment = null;
        super.onBackPressed();
    }


}