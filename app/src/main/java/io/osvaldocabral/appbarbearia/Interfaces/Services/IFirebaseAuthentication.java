package io.osvaldocabral.appbarbearia.Interfaces.Services;

import android.content.Context;
import android.content.Intent;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface IFirebaseAuthentication {

    public FirebaseUser SignIn(int requestCode, int resultCode, Intent data);
    public FirebaseUser GetUser();
    public boolean SignOut(Context context);
    public boolean Delete();
    public List<AuthUI.IdpConfig> createSignInIntent();
}
