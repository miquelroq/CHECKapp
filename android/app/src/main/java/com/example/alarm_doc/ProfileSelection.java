package com.example.alarm_doc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.example.alarm_doc.domain.Profile;
import com.example.alarm_doc.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class ProfileSelection extends AppCompatActivity {

    private LinearLayout layout;
    private Activity act = this;
    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        layout = findViewById(R.id.linear_layout);

        // Fetch every profile
        List<Profile> profiles = utils.getAllProfiles(act);

        // For each profile, inflate a view with the name and their pfp
        for (Profile p : profiles) {
            //Toast.makeText(getApplicationContext(), img_name.toString(), Toast.LENGTH_LONG).show();
            // addView("banana", img_name);

            addView(p.getName(), p.getPhoto());
        }

        // Set up the button to add a new profile
        Button add = (Button) findViewById(R.id.fab);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Launch the form activity to create a new profile
                Intent form = new Intent(getApplicationContext(), NewProfile.class);
                startActivity(form);

            }
        });


    }

    private void addView(String name, Uri img) {

        View avatar = getLayoutInflater().inflate(R.layout.avatar_img, null, false);

        TextView display_name = (TextView) avatar.findViewById(R.id.profile_name);
        display_name.setText(name);

        ImageView display_img = (ImageView) avatar.findViewById(R.id.avatar_img);
        display_img.setImageURI(img);

        // TODO: Make the picture and the text clickable and start activities

        layout.addView(avatar);

    }



}