package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class JanelaSensores extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorAcelerometro;
    private TextView Tv_Acelerometro, Tv_Magnetometro, Tv_Luz;
    private int counter = 0;
    private boolean writing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_sensores);

        Tv_Acelerometro = findViewById(R.id.tv_Acelerometro);
        Tv_Magnetometro = findViewById(R.id.tv_Magnetometro);
        Tv_Luz = findViewById(R.id.tv_Light);

        TextView texto = (TextView)findViewById(R.id.editText_debug);

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder msg = new StringBuilder();
        msg.append("Sensores no device são: \n");

        for (Sensor S : sensors) {
            msg.append("Nome: " + S.getName() + " ; Tipo:" + S.getType() + "\n");
        }
        texto.setText(msg);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Setting up buttons
        Button write = (Button) findViewById(R.id.write_file);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toggle writing of files
                if (writing) {

                    unregisterAll();
                    writing = false;
                    Uteis.MSG_DEBUG("DEBUG", "Stop writing sensor data to files");
                    Toast.makeText(getApplicationContext(), "stop writing", Toast.LENGTH_LONG).show();

                } else {

                    registerAll();
                    writing = true;
                    Uteis.MSG_DEBUG("DEBUG", "Start writing sensor data to files");
                    Toast.makeText(getApplicationContext(), "start writing", Toast.LENGTH_LONG).show();

                }
            }
        });

        //

        Button read = (Button) findViewById(R.id.read);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fileContent = readFromFile(getApplicationContext());

                // Send this extra data to a new activity where we will read the file
                Intent intent = new Intent(getBaseContext(), FileRead.class);
                intent.putExtra("FILE", fileContent);

                startActivity(intent);

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        String acel, temp, gyro, gyro_u, light;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String msg = "";

        // Fetch sensor type and build the msg
        switch(sensorEvent.sensor.getType())
        {
            case Sensor.TYPE_ACCELEROMETER:
                msg += "Accelerometer;";
                acel = "Accelerometer: " + sensorEvent.values[0] + ";" + sensorEvent.values[1] + ";" + sensorEvent.values[2] + ";"; // + timestamp
                Tv_Acelerometro.setText(acel);
                break;

            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                msg += "Temperature;";
                temp = "Temperature: " + sensorEvent.values[0] + ";";
                break;

            case Sensor.TYPE_GYROSCOPE:
                msg += "Gyroscope;";
                Uteis.MSG_DEBUG("S_GYROSCOPE", "S_GYROSCOPE = (" + sensorEvent.values[0] + "," + sensorEvent.values[1] + "," + sensorEvent.values[2] + ")");
                break;

            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                msg += "Gyroscope;";
                Uteis.MSG_DEBUG("S_GYROSCOPE_UNCALIBRATED", "S_GYROSCOPE_UNCALIBRATED = (" + sensorEvent.values[0] + "," + sensorEvent.values[1] + "," + sensorEvent.values[2] + ")");
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                msg += "Magnetic;";
                Tv_Magnetometro.setText("S_TYPE_MAGNETIC_FIELD = (" + sensorEvent.values[0] + "," + sensorEvent.values[1] + "," + sensorEvent.values[2] + ")");
                break;
            case Sensor.TYPE_LIGHT:
                msg += "Light;";
                Tv_Luz.setText("S_TYPE_LIGHT = (" + sensorEvent.values[0]+")");
                break;
        }

        // Append the data to be added depending on the sensor
        if(sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE || sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {

            msg += sensorEvent.values[0] + ";";

        } else {

            msg += sensorEvent.values[0] + ";" + sensorEvent.values[1] + ";" + sensorEvent.values[2] + ";";

        }

        // Add timestamp
        msg += timestamp.getTime() + "\n";


        // Write to file
        writeToFile(msg, getApplicationContext());

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
        mSensorManager.registerListener(this, mSensorAcelerometro, SensorManager.SENSOR_DELAY_FASTEST);
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

    // Write to file
    private void writeToFile(String data, Context context) {
        try {
            Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();

            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            Date date = calendar.getTime();

            int day = calendar.get(Calendar.DATE);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            String filename = Integer.toString(day) + Integer.toString(month) + Integer.toString(year) + "ola.txt";

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND));
            outputStreamWriter.append(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    // Read from file
    private String readFromFile(Context context) {

        String ret = "";

        try {

            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            Date date = calendar.getTime();

            int day = calendar.get(Calendar.DATE);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            String filename = Integer.toString(day) + Integer.toString(month) + Integer.toString(year) + "ola.txt";

            InputStream inputStream = context.openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(bufferedReader.readLine());
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("logging activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("logging activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    // Register all sensors
    private void registerAll() {
        mSensorAcelerometro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        //-----------
        Sensor mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this, mSensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        //-----------
        Sensor mSensorGyroscopeUncalibrate = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
        mSensorManager.registerListener(this, mSensorGyroscopeUncalibrate, SensorManager.SENSOR_DELAY_NORMAL);
        //-----------
        Sensor mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mSensorMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        //-----------
        Sensor mSensorLuz = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, mSensorLuz, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Unregister all sensors
    private void unregisterAll() {

        mSensorManager.unregisterListener(this);

    }
}
