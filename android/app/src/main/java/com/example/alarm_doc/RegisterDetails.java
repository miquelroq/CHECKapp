package com.example.alarm_doc;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alarm_doc.domain.Register;
import com.example.alarm_doc.utils.Utils;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

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

        //Scores
        int emo = register.getEmotionalScore();
        TextView emotional_tv = findViewById(R.id.emotional_score);
        emotional_tv.setText(""+"%");
        int cardio = register.getCardioRespiratoryScore();
        TextView cardio_tv = findViewById(R.id.cardio_score);
        cardio_tv.setText(""+"%");
        int fitness = (int) register.getFitnessScore();
        TextView fit_tv = findViewById(R.id.fitness_score);
        fit_tv.setText(""+"%");
        int nerves = (int) register.getNervousMuscularScore();
        TextView nerve_tv = findViewById(R.id.muscular_score);
        nerve_tv.setText(""+"%");

        // BPM
        int bpm = register.getCardioRespiratory().getBpm();
        //TextView bpm_tv = findViewById(R.id.bpm);
        //bpm_tv.setText(""+bpm);

        // Breath Rate
        int breath = register.getCardioRespiratory().getDiff();
        //TextView breath_tv = findViewById(R.id.breath);
        //breath_tv.setText(""+breath);

        BarChart mBarChart = (BarChart) findViewById(R.id.barchart);

        BarModel bpmBar = new BarModel(bpm, 0xFF123456);
        bpmBar.setLegendLabel("BPM");

        BarModel breathBar = new BarModel(breath, 0xFF123456);
        breathBar.setLegendLabel("Breath Rate");

        mBarChart.addBar(bpmBar);
        // TODO: Plot here the ideal value for bpm
        mBarChart.addBar(breathBar);
        // TODO: Plot here the ideal value for breath rate

        mBarChart.startAnimation();


        // Alpha
        int alpha = register.getNeurologic().getAlpha().getAmp();

        // Beta
        int beta = register.getNeurologic().getBeta().getAmp();

        // Delta
        int delta = register.getNeurologic().getDelta().getAmp();

        // Theta
        int theta = register.getNeurologic().getTheta().getAmp();

        // Gamma
        int gamma = register.getNeurologic().getGamma().getAmp();


        BarChart nBarChart = (BarChart) findViewById(R.id.barchart_neuro);

        BarModel alphaBar = new BarModel(alpha, 0xFF123456);
        alphaBar.setLegendLabel("Alpha");

        BarModel betaBar = new BarModel(beta, 0xFF123456);
        betaBar.setLegendLabel("Beta");

        BarModel deltaBar = new BarModel(delta, 0xFF123456);
        deltaBar.setLegendLabel("Delta");

        BarModel thetaBar = new BarModel(theta, 0xFF123456);
        thetaBar.setLegendLabel("Theta");

        BarModel gammaBar = new BarModel(gamma, 0xFF123456);
        gammaBar.setLegendLabel("Gamma");

        nBarChart.addBar(alphaBar);
        // TODO: Plot here the ideal value for alpha
        nBarChart.addBar(betaBar);
        // TODO: Plot here the ideal value for beta
        nBarChart.addBar(deltaBar);
        // TODO: Plot here the ideal value for delta
        nBarChart.addBar(thetaBar);
        // TODO: Plot here the ideal value for theta
        nBarChart.addBar(gammaBar);
        // TODO: Plot here the ideal value for gamma

        nBarChart.startAnimation();




    }
}