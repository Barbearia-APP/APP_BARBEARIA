package io.osvaldocabral.appbarbearia.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import io.osvaldocabral.appbarbearia.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;
    EstablishmentAdapter adapter;
    Task<QuerySnapshot> querySnapshotTask;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        adapter = new EstablishmentAdapter();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        

        querySnapshotTask = DataSingleton.getInstance().taskFirestore;
        querySnapshotTask.addOnCompleteListener(retrieveAndFillData());
        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}