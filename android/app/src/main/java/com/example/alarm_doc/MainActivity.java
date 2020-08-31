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

public class MainActivity extends AppCompatActivity {

    private Activity act = this;
    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Profile p = utils.getLoggedProfile(act);
        Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();

        Button diagnose = (Button) findViewById(R.id.diagnose);

        // Not the way taught in classes, needs reviewing
        diagnose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CheckupProcess.class);
                startActivity(intent);

            }

        });
    }
}