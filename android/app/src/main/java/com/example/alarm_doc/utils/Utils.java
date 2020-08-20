package com.example.alarm_doc.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alarm_doc.domain.Profile;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * Utils class primarily used to handle persistently stored data
 * in an easy way through SharedPreferences
 *
 *
 */
public class Utils {

    private Gson gson;

    public Utils() {
        this.gson = new Gson();
    }

    public void saveProfile(Profile p, Activity activity) {

        // Fetch activity's shared preferences and their editor
        SharedPreferences sharedPreferences = activity.getSharedPreferences("profiles", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();

        // Serialize the profile to be saved
        String serializedProfile = gson.toJson(p);

        // Use the profile's name as the key to fetch it later
        editor.putString(p.getName(), serializedProfile);
        editor.apply();

    }

    public List<Profile> getAllProfiles(Activity activity) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("profiles", Context.MODE_PRIVATE);
        ArrayList<Profile> ret = new ArrayList<>();

        for (Object p : sharedPreferences.getAll().values()) {

            Log.d("lolol", p.toString());

            ret.add(gson.fromJson(p.toString(), Profile.class));

        }

        return ret;

    }
}
