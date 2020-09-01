package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alarm_doc.domain.Register;
import com.example.alarm_doc.utils.Utils;

public class Healthy extends AppCompatActivity {

    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy);

        Button finish = (Button) findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // Add a new register to the logged in profile
        Register r = new Register();
        utils.addRegisterToLoggedUser(r, this);

    }
}