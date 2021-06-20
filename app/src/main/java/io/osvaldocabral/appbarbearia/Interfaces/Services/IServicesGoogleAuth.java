package io.osvaldocabral.appbarbearia.Interfaces.Services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface IServicesGoogleAuth {

    boolean SignIn(int requestCode, Activity context, Intent data);
    boolean SignOut();
    boolean Delete();
    FirebaseUser GetUser();

}
