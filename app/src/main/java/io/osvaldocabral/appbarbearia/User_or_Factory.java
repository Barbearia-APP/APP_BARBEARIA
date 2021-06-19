package io.osvaldocabral.appbarbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.osvaldocabral.appbarbearia.Enum.TypesUser;

public class User_or_Factory extends AppCompatActivity {

    Button buttonClient;
    Button buttonFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_or_factory);
        buttonClient = findViewById(R.id.buttonUser);
        buttonFactory = findViewById(R.id.buttonFactory);
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
}