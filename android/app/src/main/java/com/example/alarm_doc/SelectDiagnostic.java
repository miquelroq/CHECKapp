package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectDiagnostic extends AppCompatActivity {

    private Button daily, weekly, custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_diagnostic);

        daily = (Button) findViewById(R.id.daily);
        weekly = (Button) findViewById(R.id.weekly);
        custom = (Button) findViewById(R.id.custom);

        // Not the way taught in classes, needs reviewing
        daily.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Checkup.class);
                startActivity(intent);

            }

        });

        // Not the way taught in classes, needs reviewing
        weekly.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Checkup.class);
                startActivity(intent);

            }

        });

        // Not the way taught in classes, needs reviewing
        custom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), DiagnoseCustomizer.class);
                startActivity(intent);

            }

        });
    }
}