package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.alarm_doc.domain.Conditions;
import com.example.alarm_doc.domain.Profile;
import com.example.alarm_doc.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class NewProfileFollowUp extends AppCompatActivity {

    private Activity act = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile_follow_up);

        final Intent intent = getIntent();
        final Utils utils = new Utils();

        // Fill in the Spinner
        Spinner spinner = (Spinner) findViewById(R.id.lifestyle_selector);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.fitness_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Conditions> conditions = new ArrayList<>();

                // Fetch CheckBox values
                CheckBox hiv = (CheckBox) findViewById(R.id.prev_dis);
                CheckBox aids = (CheckBox) findViewById(R.id.prev_dis1);
                CheckBox epilepsy = (CheckBox) findViewById(R.id.prev_dis2);
                CheckBox asthma = (CheckBox) findViewById(R.id.prev_dis3);

                if(hiv.isChecked()) {
                    conditions.add(Conditions.HIV);
                }

                if(aids.isChecked()) {
                    conditions.add(Conditions.AIDS);
                }

                if(epilepsy.isChecked()) {
                    conditions.add(Conditions.EPILEPSY);
                }

                if(asthma.isChecked()) {
                    conditions.add(Conditions.ASTHMA);
                }

                // Instance a profile
                // TODO: Discuss: where do we include the lifestyle parameter?
                Profile p = new Profile(

                        intent.getStringExtra("name"),
                        intent.getBooleanExtra("female", false),
                        intent.getIntExtra("age", -1),
                        intent.getFloatExtra("weight", -1),
                        intent.getFloatExtra("height", -1),
                        (Uri) intent.getSerializableExtra("pfp"),
                        conditions

                );

                // Save profile persistently
                utils.saveProfile(p, act);

                // Launch the Profile Selection activity and display the newly created profile
                Intent selection = new Intent(getApplicationContext(), ProfileSelection.class);
                startActivity(selection);

            }
        });

    }
}