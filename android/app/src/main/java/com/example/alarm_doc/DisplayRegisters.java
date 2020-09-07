package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarm_doc.domain.Profile;
import com.example.alarm_doc.domain.Register;
import com.example.alarm_doc.utils.Utils;

import java.util.ArrayList;

/**
 *
 * Activity that displays past registers of the logged in User
 *
 * Each Register is clickable and leads to further details
 *
 */
public class DisplayRegisters extends AppCompatActivity {

    private Utils utils = new Utils();
    private Activity act = this;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registers);

        layout = findViewById(R.id.linearLayoutRegisters);

        // Fetch the logged in user
        Profile loggedInProfile = utils.getLoggedProfile(act);

        // Fetch the user's past registers
        ArrayList<Register> registers = (ArrayList<Register>) loggedInProfile.getRegisters();

        // Inflate each register into the Layout
        for (Register r : registers) {
            addView(r);
        }

    }

    private void addView(final Register r) {

        final String date = r.getDate();

        View register = getLayoutInflater().inflate(R.layout.register, null, false);
        final Intent registerDetails = new Intent(getApplicationContext(), MainActivity.class);

        TextView display_date = (TextView) register.findViewById(R.id.date);

        if(date != null) {
            display_date.setText(date);
        } else {
            display_date.setText("Esta data é inválida :)");
        }

        display_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerDetails.putExtra("date", date);
                startActivity(registerDetails);

            }
        });

        // Depending on the outcome of the register, we will be displaying different indicators
        int imgSrc = r.isHealthy() ? R.drawable.ic_arrow_left : R.drawable.ic_arrow_right;

        ImageView display_img = (ImageView) register.findViewById(R.id.displayOutcome);

        display_img.setImageResource(imgSrc);

        display_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerDetails.putExtra("date", date);
                startActivity(registerDetails);

            }
        });

        layout.addView(register);

    }
}