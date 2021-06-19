package io.osvaldocabral.appbarbearia.Services;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import io.osvaldocabral.appbarbearia.Interfaces.Services.IFirebaseAuthentication;

import static android.app.Activity.RESULT_OK;

public class  FirebaseAuthentication implements IFirebaseAuthentication {

    public static boolean isSuccess = false;
    public static final int RC_SIGN_IN = 123;
    public static FirebaseAuthentication instance;

    public static FirebaseAuthentication getInstance(){
        if (instance == null) {
            instance = new FirebaseAuthentication();
        }
        return instance;
    }

    public FirebaseUser SignIn(int requestCode, int resultCode, Intent data) {
        FirebaseUser user = null;
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                user = FirebaseAuth.getInstance().getCurrentUser();
                return user;
                // ...
            } else {
                return user;
            }
        }
        return user;
    }

    @Override
    public FirebaseUser GetUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user;
    }

    @Override
    public boolean SignOut(Context context) {
        isSuccess = false;
        AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        isSuccess = true;
                    }

                });

        return isSuccess;
    }

    @Override
    public boolean Delete() {
        isSuccess = false;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            isSuccess =true;
                        }
                    }
                });
        return isSuccess;

    }

    @Override
    public List<AuthUI.IdpConfig> createSignInIntent() {

         List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
         return providers;
    }
}
