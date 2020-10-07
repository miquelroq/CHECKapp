package com.example.alarm_doc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alarm_doc.domain.Conditions;
import com.example.alarm_doc.domain.Profile;
import com.example.alarm_doc.utils.Utils;

import java.util.ArrayList;

/**
 *
 * New Profile form
 *
 * Processes, validates and collects the data inserted when creating a new profile
 *
 * Adds a new profile to SharedPreferences via Utils
 *
 *
 */
public class NewProfile extends AppCompatActivity {

    private final int MIN_NAME_LENGTH = 3;
    private final int MAX_NAME_LENGTH = 18;
    private final int MIN_AGE = 0;
    private final int MAX_AGE = 99;
    private final int MIN_HEIGHT = 30;
    private final int MAX_HEIGHT = 250;
    private final int MIN_WEIGHT = 20;
    private final int MAX_WEIGHT = 140;
    private final int REQUEST_GET_SINGLE_FILE = 1;

    public Uri img_name = null;
    public Utils utils = new Utils();
    public Activity act = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);


        // Go back
        Button back = (Button) findViewById(R.id.closeBtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        // Fill in the sex Spinner
        Spinner sex = (Spinner) findViewById(R.id.sex_select);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sex_adapter = ArrayAdapter.createFromResource(this,
                        R.array.sex_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        sex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sex.setAdapter(sex_adapter);

        // Fill in the lifestyle Spinner
        final Spinner lifestyle = (Spinner) findViewById(R.id.lifestyle_selector);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.fitness_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        lifestyle.setAdapter(adapter);

        // Configure buttons
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start the next activity if all the data is valid, else just display an error message
                boolean valid = true;

                // Validate name (needs to be longer than 3 chars and shorter than 18)
                EditText name = (EditText) findViewById(R.id.name_select);
                String input_name = name.getText().toString();

                if(input_name.length() > MAX_NAME_LENGTH || input_name.length() < MIN_NAME_LENGTH) {
                    valid = false;
                }

                // Validate the age (must be between 0 and 99)
                EditText age = (EditText) findViewById(R.id.age_select);
                int input_age = age.getText().toString().equals("") ? -1 : Integer.parseInt(age.getText().toString());

                if(input_age < MIN_AGE || input_age > MAX_AGE) {
                    valid = false;
                }

                // Needs no validation, I think
                Spinner sex = (Spinner) findViewById(R.id.sex_select);
                boolean female = sex.getSelectedItem().toString().toLowerCase().equals("female");

                // Validate the height (must be between 30 and 250 cm)
                EditText height = (EditText) findViewById(R.id.height_select);
                float input_height = height.getText().toString().equals("") ? -1 : Float.parseFloat(height.getText().toString());

                if(input_height < MIN_HEIGHT || input_height > MAX_HEIGHT) {
                    valid = false;
                }

                // Validate the weight (must be between 20 and 140 kg)
                EditText weight = (EditText) findViewById(R.id.weight_select);
                float input_weight = weight.getText().toString().equals("") ? -1 : Float.parseFloat(weight.getText().toString());

                if(input_weight < MIN_WEIGHT || input_weight > MAX_WEIGHT) {
                    valid = false;
                }

                // Parse selected lifestyle
                int activityLevel = -1;
                String selectedLifestyle = lifestyle.getSelectedItem().toString();

                // Awful. Ideal solution would be to associate each <item> with an integer
                // and simply fetch that integer.
                switch (selectedLifestyle) {
                    case "Not active":
                        activityLevel = 0;
                        break;

                    case "Barely active":
                        activityLevel = 1;
                        break;

                    case "Somewhat active":
                        activityLevel = 2;
                        break;

                    case "Moderately active":
                        activityLevel = 3;
                        break;

                    case "Highly active":
                        activityLevel = 4;
                        break;

                    case "Extremely active":
                        activityLevel = 5;
                        break;
                }


                // Validate and extract previous diseases
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


                // Launch the Profile Selection activity and display the newly created profile
                Intent selection = new Intent(getApplicationContext(), ProfileSelection.class);
                startActivity(selection);

                // // // // // // // // // // // //

                if(valid) {

                    // Instance a profile

                    Profile p = new Profile(
                                                input_name,
                                                female,
                                                input_age,
                                                input_weight,
                                                input_height,
                            activityLevel,
                            img_name.toString(),
                                                conditions
                                        );

                    // Save profile persistently
                    utils.saveProfile(p, act);

                    return;

                }

                // TODO: Get better error messages or UI cues on what to fix
                Toast.makeText(getApplicationContext(), "Error! Fix the invalid fields", Toast.LENGTH_LONG).show();

            }
        });

        Button uploadPicture = (Button) findViewById(R.id.uploadPic);
        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GET_SINGLE_FILE);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GET_SINGLE_FILE) {

            // Fetch selected picture
            img_name = data.getData();
            Toast.makeText(getApplicationContext(), img_name.toString(), Toast.LENGTH_LONG).show();

            ImageView pfp_prev = (ImageView) findViewById(R.id.pfp_preview);

            if (img_name != null) {
                pfp_prev.setImageURI(img_name);
            }

        }
    }
}