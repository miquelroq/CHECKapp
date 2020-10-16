package com.example.alarm_doc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // white font on status bar

        Profile p = utils.getLoggedProfile(act);
        Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();

        // Go back
        Button back = (Button) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }

        });

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

        // Set image source to logged in profile
        ImageView imgview = (ImageView) findViewById(R.id.avatar_img);
        Uri img = Uri.parse(p.getPhoto());

        imgview.setImageURI(img);
    }
}