package io.osvaldocabral.appbarbearia.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.CollectionReference;

import java.util.HashMap;
import java.util.Map;

import io.osvaldocabral.appbarbearia.Components.EstablishmentAdapter;
import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.Model.Establishment;
import io.osvaldocabral.appbarbearia.Pages.CreateService;
import io.osvaldocabral.appbarbearia.Pages.EstablishmentDetail;
import io.osvaldocabral.appbarbearia.R;
import io.osvaldocabral.appbarbearia.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    Button buttonSave;
    EditText editTextName, editTextAddress, editTextPhone;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        buttonSave = view.findViewById(R.id.buttonSaveFragment);
        editTextName = view.findViewById(R.id.editTextNameFragment);
        editTextAddress = view.findViewById(R.id.editTextAddressFragment);
        editTextPhone = view.findViewById(R.id.editTextPhoneFragment);

        return view;
    }


    public void saveEstablishmentClickedMain(View view) {
        CollectionReference collection = DataSingleton.getInstance().firestore.collection("establishments");
        collection.add(filEstablishment());
        clearFields();
    }


    public Map<String, Object> filEstablishment() {
        Map<String, Object> establishmentAux = new HashMap<>();
        establishmentAux.put("name", editTextName.getText().toString());
        establishmentAux.put("address", editTextAddress.getText().toString());
        establishmentAux.put("phone", editTextPhone.getText().toString());

        return establishmentAux;
    }


    public void clearFields() {
        editTextName.setText("");
        editTextAddress.setText("");
        editTextPhone.setText("");
    }

}