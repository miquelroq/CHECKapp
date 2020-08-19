package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FileRead extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_read);

        TextView tv = (TextView) findViewById(R.id.content);

        tv.setText(getIntent().getStringExtra("FILE"));
    }
}