package io.osvaldocabral.appbarbearia.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.osvaldocabral.appbarbearia.Model.Establishment;
import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.R;


public class EstablishmentDetail extends AppCompatActivity {


    Button buttonSave;
    EditText editTextName, editTextAddress, editTextPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_detail);

        buttonSave = findViewById(R.id.buttonSave);
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
    }


    public void saveEstablishmentClicked(View view) {
        String name = editTextName.getText().toString();
        String address = editTextAddress.getText().toString();
        String phone = editTextPhone.getText().toString();

        Establishment establishment = new Establishment(name, address, phone, "");
        DataSingleton.getInstance().firestore.collection("establishments").add(establishment);
    }


}