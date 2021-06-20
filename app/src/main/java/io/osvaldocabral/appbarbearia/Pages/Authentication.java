package io.osvaldocabral.appbarbearia.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import io.osvaldocabral.appbarbearia.AdminNavigation;
import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.R;
import io.osvaldocabral.appbarbearia.Services.ServiceGoogleAuth;
import io.osvaldocabral.appbarbearia.User_or_Factory;

public class Authentication extends AppCompatActivity {

    private ServiceGoogleAuth serviceGoogleAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.authentication);
        String teste = getIntent().getStringExtra("typeuser");
        serviceGoogleAuth = ServiceGoogleAuth.getInstance(this);

    }



    public void signIn(View view) {
        Intent signInIntent = serviceGoogleAuth.mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, serviceGoogleAuth.RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean Auth = serviceGoogleAuth.SignIn(requestCode,this,data);
        Intent intent = new Intent(Authentication.this, AdminNavigation.class);
        this.startActivity(intent);
    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(serviceGoogleAuth.GetUser()!=null){
            DataSingleton.getInstance().user = serviceGoogleAuth.GetUser();
            Intent intent = new Intent(Authentication.this, AdminNavigation.class);
            this.startActivity(intent);
        }

    }


}