package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alarm_doc.domain.Profile;
import com.example.alarm_doc.utils.Utils;

/**
 *
 * Main Menu Activity
 *
 * Essentially, offers three options:
 *  * Run a Diagnostic
 *  * Check out past registers
 *  * Access the logged in profile to edit data (TODO)
 *
 */
public class MainActivity extends AppCompatActivity {

    private Activity act = this;
    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Profile p = utils.getLoggedProfile(act);
        Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();

        // Run a Diagnostic
        Button diagnose = (Button) findViewById(R.id.diagnose);

        diagnose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent diag = new Intent(getApplicationContext(), CheckupProcess.class);
                startActivity(diag);

            }

        });

        // Check out past registers
        Button statistics = (Button) findViewById(R.id.stats);

        statistics.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent stats = new Intent(getApplicationContext(), DisplayRegisters.class);
                startActivity(stats);

            }

        });
    }
}