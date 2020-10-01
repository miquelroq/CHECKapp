package com.example.alarm_doc.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.alarm_doc.domain.Profile;
import com.example.alarm_doc.domain.Register;
import com.google.gson.Gson;

import java.util.ArrayList;
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

    // Used when a new Profile is created
    public void saveProfile(Profile p, Activity activity) {

        // Fetch activity's shared preferences and their editor
        SharedPreferences sharedPreferences = activity.getSharedPreferences("profiles", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        
        // Serialize the profile to be saved
        String serializedProfile = gson.toJson(p);

        // Use the profile's name as the key to fetch it later
        editor.putString(p.getName(), serializedProfile);
        editor.apply();

    }

    // Used to delete a profile
    public void deleteProfile(String username, Activity activity) {

        // Fetch activity's shared preferences and their editor
        SharedPreferences sharedPreferences = activity.getSharedPreferences("profiles", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Find and delete the user
        editor.remove(username);
        editor.apply();;

    }

    // Used to display all the profiles stored in shared preferences (mainly in the ProfileSelection activity)
    public List<Profile> getAllProfiles(Activity activity) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("profiles", Context.MODE_PRIVATE);

        ArrayList<Profile> ret = new ArrayList<>();

        for (Object p : sharedPreferences.getAll().values()) {

            ret.add(gson.fromJson(p.toString(), Profile.class));

        }

        return ret;

    }

    // Set up the logged in user "Global Variable"
    public void logIn(Profile p, Activity activity) {

        // Fetch activity's shared preferences and their editor
        SharedPreferences sharedPreferences = activity.getSharedPreferences("logged_user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Serialize the profile to be saved
        String serializedProfile = gson.toJson(p);

        // Use an easy macro as the key to fetch it later
        editor.putString("LOGGED_IN_USER", serializedProfile);
        editor.apply();

    }

    // Get the profile that is logged in
    public Profile getLoggedProfile(Activity activity) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("logged_user", Context.MODE_PRIVATE);
        ArrayList<Profile> ret = new ArrayList<>();

        String serializedProfile = sharedPreferences.getString("LOGGED_IN_USER", "default");
        return gson.fromJson(serializedProfile, Profile.class);

    }


    public void addRegisterToLoggedUser(Register r, Activity activity) {

        // Fetch the logged in user
        Profile p = getLoggedProfile(activity);

        // Delete profile p from SharedPreferences
        deleteProfile(p.getName(), activity);

        // Update p's registers
        p.addRegister(r);

        // Save p
        saveProfile(p, activity);

        // Login with p
        logIn(p, activity);

    }
}
