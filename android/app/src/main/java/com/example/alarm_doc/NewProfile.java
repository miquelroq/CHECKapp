package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *
 ** For the NewProfile activity, since we have multiple pages
 * we can use fragments, which work like Activity subcomponents.
 *
 * Check:
 * https://developer.android.com/training/basics/fragments/creating
 * https://developer.android.com/guide/components/fragments
 *
 *
 */
public class NewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        RegisterFragment register = new RegisterFragment();
        RegisterFollowUpFragment registerFollowUp = new RegisterFollowUpFragment();

        // Sequencing fragments inside this activity
        getSupportFragmentManager().beginTransaction()
                .add(R.id.newProfileContainer, register)
                .add(R.id.registerFollowUp, registerFollowUp)
                .commit();
    }
}