package io.osvaldocabral.appbarbearia.Pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import io.osvaldocabral.appbarbearia.Components.EstablishmentAdapter;
import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.Model.Establishment;
import io.osvaldocabral.appbarbearia.R;

public class ListEstabelecimento extends AppCompatActivity {

    RecyclerView recyclerView;
    EstablishmentAdapter adapter;
    Task<QuerySnapshot> querySnapshotTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_estabelecimento);
        setTitle("Todos Estabelecimentos");

        adapter = new EstablishmentAdapter();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        querySnapshotTask = DataSingleton.getInstance().taskFirestore;
        querySnapshotTask.addOnCompleteListener(retrieveAndFillData());

    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private OnCompleteListener<QuerySnapshot> retrieveAndFillData() {
        return new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    DataSingleton.getInstance().listEstablishment.add(
                            new Establishment((String) document.getData().get("name"),
                                    (String) document.getData().get("address"),
                                    (String) document.getData().get("phone"),
                                    "/storage/emulated/0/Android/data/io.osvaldocabral.validadordepresenca/files/Pictures/pic_202106132046382536392854497873818.jpg"
                            )
                    );

                    adapter.notifyDataSetChanged();
                }
            }
        };
    }


}