package io.osvaldocabral.appbarbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.osvaldocabral.appbarbearia.Enum.TypesUser;
import io.osvaldocabral.appbarbearia.Pages.Authentication;
import io.osvaldocabral.appbarbearia.Services.ServiceGoogleAuth;

public class User_or_Factory extends AppCompatActivity {

    Button buttonClient;
    Button buttonFactory;
    ServiceGoogleAuth serviceGoogleAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_or_factory);
        buttonClient = findViewById(R.id.buttonUser);
        buttonFactory = findViewById(R.id.buttonFactory);
        serviceGoogleAuth = ServiceGoogleAuth.getInstance(this);
    }

    public void onClickButtonClient(View view){
        String typesUser;
        if(view.getId()==buttonClient.getId()){
        typesUser = TypesUser.CLIENT.getDescription();
        }else{
        typesUser = TypesUser.FACTORY.getDescription();
        }

        Intent intent = new Intent(this, Authentication.class);
        intent.putExtra("typeuser",typesUser);
        this.startActivity(intent);

    }

    @Override
    public void onStart() {
        super.onStart();
        if(serviceGoogleAuth.GetUser()!=null){
            DataSingleton.getInstance().user = serviceGoogleAuth.GetUser();
            Intent intent = new Intent(User_or_Factory.this,AdminNavigation.class);
            this.startActivity(intent);
        }

    }
}