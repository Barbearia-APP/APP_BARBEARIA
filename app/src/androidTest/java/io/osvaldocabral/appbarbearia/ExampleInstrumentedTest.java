package io.osvaldocabral.appbarbearia;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import io.osvaldocabral.appbarbearia.Model.Establishment;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("io.osvaldocabral.appbarbearia", appContext.getPackageName());

        //insertEstablishment();
        updateEstablishment();
    }


    public void updateEstablishment() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Novo Update 2");
        DataSingleton.getInstance().firestore.collection("establishments")
                .document("2lNrpuaNxzkKGHZVGpNf").update(map)
                .onSuccessTask(new SuccessContinuation<Void, Object>() {

                    @NonNull
                    @Override
                    public Task<Object> then(@Nullable Void aVoid) throws Exception {
                        Log.d("TESTE", "chegou");
                        return null;
                    }
                });
    }


    public void insertEstablishment() {
        DataSingleton.getInstance().firestore.collection("establishments")
                .add(
                        new Establishment("insertEstablishment",
                                "insertEstablishment",
                                "insertEstablishment",
                                "insertEstablishment"
                        )
                );
    }

}