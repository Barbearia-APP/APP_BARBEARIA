package io.osvaldocabral.appbarbearia.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import io.osvaldocabral.appbarbearia.Components.EstablishmentAdapter;
import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.Model.Establishment;
import io.osvaldocabral.appbarbearia.Pages.EstablishmentDetail;
import io.osvaldocabral.appbarbearia.R;

public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        DataSingleton.getInstance().establishmentAdapter = new EstablishmentAdapter();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(DataSingleton.getInstance().establishmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        DataSingleton.getInstance().establishmentAdapter.setClickListenner(new EstablishmentAdapter.ClickListenner() {
            @Override
            public void onItemClick(int position, View view) {
                DataSingleton.getInstance().currentEstablishment = DataSingleton.getInstance().listEstablishment.get(position);
                Intent intent = new Intent(getContext(), EstablishmentDetail.class);
                startActivity(intent);
            }
        });

        DataSingleton.getInstance().taskFirestore.addOnCompleteListener(retrieveAndFillData());

        return view;
    }


    private OnCompleteListener<QuerySnapshot> retrieveAndFillData() {
        return new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                DataSingleton.getInstance().listEstablishment.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    DataSingleton.getInstance().listEstablishment.add(
                            new Establishment(
                                    (String) document.getId(),
                                    (String) document.getData().get("name"),
                                    (String) document.getData().get("address"),
                                    (String) document.getData().get("phone"),
                                    ""
                            )
                    );
                }
                DataSingleton.getInstance().establishmentAdapter.notifyDataSetChanged();
            }
        };
    }


    @Override
    public void onDestroyView() {
        DataSingleton.getInstance().establishmentAdapter.notifyDataSetChanged();
        super.onDestroyView();
    }


    @Override
    public void onResume() {
        DataSingleton.getInstance().taskFirestore.addOnCompleteListener(retrieveAndFillData());
        super.onResume();
    }
}