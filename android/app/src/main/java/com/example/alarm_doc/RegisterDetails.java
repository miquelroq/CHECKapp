package com.example.alarm_doc;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alarm_doc.domain.Register;
import com.example.alarm_doc.utils.Utils;

public class RegisterDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        Bundle extras = getIntent().getExtras();
        Utils utils = new Utils();
        String date = null;

        if (extras != null) {
            date = extras.getString("date");
        } else {
            Log.e("Invalid date", "Invalid date");
            return;
        }

        Register register = utils.getRegisterByDate(date, this);

        if (register == null) {
            Log.e("Invalid date", "Invalid date");
            return;
        }

        Log.d("REGISTER FIXE", register.toString());


        // BPM
        int bpm = register.getCardioRespiratory().getBpm();
        TextView bpm_tv = findViewById(R.id.bpm);
        bpm_tv.setText(""+bpm);

        // Breath Rate
        int breath = register.getCardioRespiratory().getDiff();
        TextView breath_tv = findViewById(R.id.breath);
        breath_tv.setText(""+breath);


        // Alpha
        int alpha = register.getNeurologic().getAlpha().getAmp();
        TextView alpha_tv = findViewById(R.id.alpha);
        alpha_tv.setText(""+alpha);

        // Beta
        int beta = register.getNeurologic().getBeta().getAmp();
        TextView beta_tv = findViewById(R.id.beta);
        beta_tv.setText(""+beta);

        // Delta
        int delta = register.getNeurologic().getDelta().getAmp();
        TextView delta_tv = findViewById(R.id.delta);
        delta_tv.setText(""+delta);

        // Theta
        int theta = register.getNeurologic().getTheta().getAmp();
        TextView theta_tv = findViewById(R.id.theta);
        theta_tv.setText(""+theta);

        // Gamma
        int gamma = register.getNeurologic().getGamma().getAmp();
        TextView gamma_tv = findViewById(R.id.gamma);
        gamma_tv.setText(""+gamma);




    }
}