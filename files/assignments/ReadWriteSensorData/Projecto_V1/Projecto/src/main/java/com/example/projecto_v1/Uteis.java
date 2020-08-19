package com.example.projecto_v1;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class Uteis {

    public static void MSG(Context cont, String s)
    {
        Toast.makeText(cont, "IIII: " + s, Toast.LENGTH_LONG).show();
    }
    public static void MSG_DEBUG(String TAG, String msg)
    {
        Log.d(TAG, msg);
    }

}
