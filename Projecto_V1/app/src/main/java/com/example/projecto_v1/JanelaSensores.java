package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class JanelaSensores extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorAcelerometro;
    private Button Botao_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_sensores);

        Botao_output = (Button)findViewById(R.id.bt_output);
        TextView texto = (TextView)findViewById(R.id.editText_debug);

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> Sensores = manager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder Mensagem = new StringBuilder();
        Mensagem.append("Sensores no device são: \n");
        for (Sensor S : Sensores) {
            Mensagem.append("Nome:" + S.getName() + " Tipo:" + S.getType() + "\n");
        }
        texto.setText(Mensagem);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAcelerometro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Sensor ss = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorManager.registerListener(this, mSensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        //mSensorManager.registerListener(this, ss, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch(sensorEvent.sensor.getType())
        {
            case Sensor.TYPE_ACCELEROMETER:
                Botao_output.setText("S =" + sensorEvent.sensor.getName() + "(" + sensorEvent.values[0] + "," + sensorEvent.values[1] + "," + sensorEvent.values[2] + ")");
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                Botao_output.setText("S =" + sensorEvent.values[0] + ")");
                break;

        }
    }

    //------------------------------------------------------
    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }
    //------------------------------------------------------
    @Override
    public void onClick(View view) {

    }
    //------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }
    //------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    //------------------------------------------------------
    //------------------------------------------------------
    //------------------------------------------------------
    //------------------------------------------------------
}
