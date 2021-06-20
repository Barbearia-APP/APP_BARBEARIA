package io.osvaldocabral.appbarbearia.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.Pages.CreateDateTime;
import io.osvaldocabral.appbarbearia.Pages.CreateService;
import io.osvaldocabral.appbarbearia.R;
import io.osvaldocabral.appbarbearia.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private Button buttonService;
    private Button buttonHorario;
    private ImageView imageView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        buttonHorario = view.findViewById(R.id.buttonHorario);
        buttonService = view.findViewById(R.id.buttonService);
        buttonService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    Intent intent = new Intent(getActivity(), CreateService.class);
                    startActivity(intent);

            }
        });

        buttonHorario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(getActivity(), CreateDateTime.class);
                startActivity(intent);

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}