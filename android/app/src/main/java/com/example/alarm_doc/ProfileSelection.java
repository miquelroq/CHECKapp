package com.example.alarm_doc;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

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

public class ProfileSelection extends AppCompatActivity {

    private static final int REQUEST_GET_SINGLE_FILE = 1;
    LinearLayout layout;
    public static final int PICK_IMAGE = 1;
    public static Uri img_name = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        layout = findViewById(R.id.linear_layout);

/*        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GET_SINGLE_FILE);*/

        // Fetch every profile

        // For each profile, inflate a view with the name and their pfp
        for (int i = 0; i < 3; i++) {
            //Toast.makeText(getApplicationContext(), img_name.toString(), Toast.LENGTH_LONG).show();
            addView("banana", img_name);
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
        // display_img.setImageBitmap(img);
        display_img.setImageURI(img);

        layout.addView(avatar);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GET_SINGLE_FILE) {
            // Fetch selected picture
            // Toast.makeText(getApplicationContext(), data.getDataString(), Toast.LENGTH_LONG).show();

            img_name = data.getData();
            Toast.makeText(getApplicationContext(), img_name.toString(), Toast.LENGTH_LONG).show();

        }
    }

}