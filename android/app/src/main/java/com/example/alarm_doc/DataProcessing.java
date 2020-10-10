package com.example.alarm_doc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.alarm_doc.domain.CardioRespiratory;
import com.example.alarm_doc.domain.Emotional;
import com.example.alarm_doc.domain.Fitness;
import com.example.alarm_doc.domain.NervousMuscular;
import com.example.alarm_doc.domain.Neurologic;
import com.example.alarm_doc.domain.Register;
import com.example.alarm_doc.services.BitalinoCapture;
import com.example.alarm_doc.utils.Utils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Process signals in order to extract the most relevant features to be sent to the API
 * Send to the API
 * GET the response
 * Launch activity according to the response/outcome
 *
 *
 */

// TODO:
//      * Send the collected data to Django API
//      * Wait for the response
//      * Launch Healthy/Unhealthy activity according to the received response
public class DataProcessing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_processing);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final Utils utils = new Utils();
        final Activity act = this;

        // When we receive the collected data, send it to the API
        LocalBroadcastManager.getInstance(DataProcessing.this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(final Context context, Intent intent) {

                        HashMap<Integer, ArrayList<Integer>> values = (HashMap<Integer, ArrayList<Integer>>) intent.getExtras().get(BitalinoCapture.ACTION_CAPTURE);

                        String eeg = values.get(1).toString();
                        String ecg = values.get(3).toString();

                        int bpm, diff, alpha, beta, delta, gamma, theta;
                        bpm = diff = alpha = beta = delta = gamma = theta = 0;

                        Log.d("arraysFixes",eeg);
                        Log.d("arraysFixes",ecg);

                        // Send this req to the API
                        String myBody = "{\n    \"eeg\":" + eeg + ",\n    \"ecg\":" + ecg + "\n}";

                        // writeToFile(myBody, context);

                        myBody = readFromFile(context);

                        Unirest.setTimeouts(0, 0);
                        Log.d("myBody", myBody);

                        try {
                            HttpResponse<String> response = Unirest.post("http://checkapp.pythonanywhere.com/api/generate")
                                    .header("Content-Type", "application/json")
                                    .body(myBody)
                                    .asString();

                            Toast.makeText(context, ""+response.getCode(), Toast.LENGTH_LONG).show();

                            Log.d("respostaFixe", response.getBody());

                            Map<String, Object> retMap = new Gson().fromJson(
                                    response.getBody(), new TypeToken<HashMap<String, Object>>() {}.getType()
                            );

                            bpm = retMap.get("bpm") == null ? null : ((Double) retMap.get("bpm")).intValue();
                            diff = ((String) retMap.get("breathrate")).equals("NaN") ? 0 : ((Double) retMap.get("breathrate")).intValue();

                            alpha = retMap.get("alpha") == null ? null : ((Double) retMap.get("alpha")).intValue();
                            beta = retMap.get("beta") == null ? null : ((Double) retMap.get("beta")).intValue();
                            delta = retMap.get("delta") == null ? null : ((Double) retMap.get("delta")).intValue();
                            theta = retMap.get("theta") == null ? null : ((Double) retMap.get("theta")).intValue();
                            gamma = retMap.get("gamma") == null ? null : ((Double) retMap.get("gamma")).intValue();




                        } catch (UnirestException e) {
                            e.printStackTrace();

                            Toast.makeText(context, "Error connecting to API", Toast.LENGTH_LONG).show();
                            Intent main = new Intent(context, MainActivity.class);
                            startActivity(main);
                        }

                        // When data is received, store it in shared preferences and launch new activity
                        Register r = new Register(
                            new Emotional(),
                            new NervousMuscular(utils.getLoggedProfile(act)),
                            new Neurologic(utils.getLoggedProfile(act), alpha, beta, delta, gamma, theta),
                            new Fitness(utils.getLoggedProfile(act)),
                            new CardioRespiratory(utils.getLoggedProfile(act), bpm, diff)
                        );

                        utils.addRegisterToLoggedUser(r, act);

                        // Launch DataDisplay activity with the latest register
                        Intent registerDetails = new Intent(getApplicationContext(), RegisterDetails.class);
                        registerDetails.putExtra("date", "LAST");
                        startActivity(registerDetails);

                    }
                }, new IntentFilter(BitalinoCapture.ACTION_CAPTURE)
        );
    }


    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("reading.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("reading.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}