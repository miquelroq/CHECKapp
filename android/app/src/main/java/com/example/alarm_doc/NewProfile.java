package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class NewProfile extends AppCompatActivity {

    private final int MIN_NAME_LENGTH = 3;
    private final int MAX_NAME_LENGTH = 18;
    private final int MIN_AGE = 0;
    private final int MAX_AGE = 99;
    private final int MIN_HEIGHT = 30;
    private final int MAX_HEIGHT = 250;
    private final int MIN_WEIGHT = 20;
    private final int MAX_WEIGHT = 140;

    private static final int REQUEST_GET_SINGLE_FILE = 1;
    public static Uri img_name = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        // Fill in the Spinner
        Spinner spinner = (Spinner) findViewById(R.id.sex_select);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.sex_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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

                // // // // // // // // // // // //

                if(valid) {

                    Intent followUp = new Intent(getApplicationContext(), NewProfileFollowUp.class);

                    followUp.putExtra("name", input_name);
                    followUp.putExtra("age", input_age);
                    followUp.putExtra("female", female);
                    followUp.putExtra("height", input_height);
                    followUp.putExtra("weight", input_weight);
                    followUp.putExtra("pfp", img_name);

                    startActivity(followUp);
                    return;

                }

                // TODO: Get better error messages or UI cues on what to fix
                Toast.makeText(getApplicationContext(), "Error! Fix the invalid fields", Toast.LENGTH_LONG).show();

            }
        });

        Button uploadPicture = (Button) findViewById(R.id.uploadPic);
        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
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