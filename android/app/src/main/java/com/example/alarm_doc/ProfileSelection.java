package com.example.alarm_doc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alarm_doc.domain.Profile;
import com.example.alarm_doc.utils.Utils;

import java.io.IOException;
import java.util.List;

/**
 *
 * Launcher activity
 *
 * Just displays the registered profiles in a Layout
 *
 * TODO: Limit profiles to be displayed in a single line
 *
 *
 */
public class ProfileSelection extends AppCompatActivity {

    private LinearLayout layout;
    private Activity act = this;
    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getIntent().setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // white font on status bar

        layout = findViewById(R.id.linear_layout);

        // Fetch every profile
        List<Profile> profiles = utils.getAllProfiles(act);

        // For each profile, inflate a view with the name and their pfp
        for (Profile p : profiles)
            addView(p);

        // Set up the button to add a new profile
        ImageButton add = (ImageButton) findViewById(R.id.fab);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Launch the form activity to create a new profile
                Intent form = new Intent(getApplicationContext(), NewProfile.class);
                startActivity(form);

            }
        });

    }

    private void addView(final Profile p) {

        String name = p.getName();
        Uri img = Uri.parse(p.getPhoto());

        View avatar = getLayoutInflater().inflate(R.layout.avatar_img, null, false);
        final Intent mainMenu = new Intent(getApplicationContext(), MainActivity.class);

        TextView display_name = (TextView) avatar.findViewById(R.id.profile_name);
        display_name.setText(name);

        display_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Configure the logged in user
                utils.logIn(p, act);

                // Launch the main menu
                startActivity(mainMenu);

            }
        });

        ImageView display_img = (ImageView) avatar.findViewById(R.id.avatar_img);

        try {

            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), img);
            Bitmap cropped = utils.getRoundedCroppedBitmap(bitmap);
            display_img.setImageBitmap(cropped);

        } catch (IOException e) {
            e.printStackTrace();
            display_img.setImageURI(img);
        }

        display_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Configure the logged in user
                utils.logIn(p, act);

                // Launch the main menu
                startActivity(mainMenu);

            }
        });

        layout.addView(avatar);

    }



}