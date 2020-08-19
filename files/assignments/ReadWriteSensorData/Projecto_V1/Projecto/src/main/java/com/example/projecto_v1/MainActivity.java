package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Timer tm;
    TextView TXV;
    static String filename = "PreferenciasFM";
    SharedPreferences Pref = null;
    int PONTUACAO = -1;
    String MEDIA;
    Random gerador;
    Gestao Gestor_IVAN;
    TextToSpeech textToSpeech = null;

    private void Texto_to_Speak(String Texto) {
        if (textToSpeech == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(Texto, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else {
            textToSpeech.speak(Texto, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    class GerarDados extends TimerTask
    {
        int i = 0;
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Uteis.MSG_DEBUG("TEMPO", "" + gerador.nextInt(100));
                    TXV.setText(""+i);
                    int N_SENSOR = gerador.nextInt(5);
                    int VALOR_SENSOR = gerador.nextInt(100);
                    Gestor_IVAN.Toma_La_Valores(N_SENSOR, VALOR_SENSOR);
                    String S = ""+N_SENSOR+","+VALOR_SENSOR;
                    Gestor_IVAN.Toma_La_Valores(S);
                    i++;
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        findViewById(R.id.Btn_Nova_Janela).setOnClickListener(this);
        findViewById(R.id.Btn_Sair).setOnClickListener(this);
        findViewById(R.id.Btn_Listagem).setOnClickListener(this);
        findViewById(R.id.Btn_Configuracoes).setOnClickListener(this);
        findViewById(R.id.Btn_Run_Task).setOnClickListener(this);
        findViewById(R.id.Btn_Sensores).setOnClickListener(this);
        findViewById(R.id.Btn_Vibrar).setOnClickListener(this);
        findViewById(R.id.Btn_Bluetooth).setOnClickListener(this);
        findViewById(R.id.Btn_Graficos).setOnClickListener(this);
        findViewById(R.id.Btn_GPS).setOnClickListener(this);

        gerador = new Random();
        tm = new Timer();
        TXV = findViewById(R.id.Txtv_ECG);
        TXV.setText("ola");

        Pref = getSharedPreferences(filename, 0);
        PONTUACAO = Pref.getInt("PONTUACAO", 0); // 0 e o valor por defeito no caso de nao existir
        MEDIA = Pref.getString("MEDIA", "");
        Gestor_IVAN = new Gestao();
    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.Btn_Nova_Janela:
                Uteis.MSG(getApplicationContext(), "Nova Janela!");
                break;
            case R.id.Btn_Sair:
                Uteis.MSG(getApplicationContext(), "Sair da APP!");
                finish();
                break;
            case R.id.Btn_Listagem:
                Uteis.MSG(getApplicationContext(), "Listagem!");
                break;
            case R.id.Btn_Configuracoes:
                Uteis.MSG(getApplicationContext(), "configurações!");
                Intent Jan = new Intent(this, JanelaConfiguracoes.class);
                Jan.putExtra("EMAIL_DEF", "ola@gmail.com");
                Jan.putExtra("TELEFONE", "123456789");
                String res = "";
                for (int i = 0; i < 10; i++)
                    res += "NOME_"+i+";";
                Jan.putExtra("NOMES", res);
                startActivity(Jan);
                break;
            case R.id.Btn_Run_Task:
                Uteis.MSG(getApplicationContext(), "GerarDados!");
                tm.schedule(new GerarDados(), 0, 500);
                break;
            case R.id.Btn_Sensores:
                Uteis.MSG(getApplicationContext(), "Sensores!");
                startActivity(new Intent(this, JanelaSensores.class));
                break;
            case R.id.Btn_Vibrar:
                Uteis.MSG(getApplicationContext(), "Vibrar!");
                Texto_to_Speak("On Vibrate");
                FuncaoVibrar();
                break;
            case R.id.Btn_Bluetooth:
                Uteis.MSG(getApplicationContext(), "Bluetooth!");
                startActivity(new Intent(this, JanelaBluetooth.class));
                break;
            case R.id.Btn_Graficos:
                Uteis.MSG(getApplicationContext(), "Gráficos!");
                startActivity(new Intent(this, JanelaGraficos.class));
                break;
            case R.id.Btn_GPS:
                Uteis.MSG(getApplicationContext(), "GPS!");
                startActivity(new Intent(this, JanelaGPS.class));
                break;
        }
    }
    public void FuncaoVibrar()
    {
        Vibrator Vibrar = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        /*
        long milliseconds = 500;
        Vibrar.vibrate(milliseconds);
        */
        long[] pattern = {0, 100, 1000, 300, 200, 100, 500, 200, 100};
        // long[] pattern = {0, 500, 300, 1000, 500};
        Vibrar.vibrate(pattern, 0);
    }
    //------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        Uteis.MSG(getApplicationContext(), "onStart");
        Texto_to_Speak("On Start");
    }

    //------------------------------------------------------
    @Override
    protected void onStop() {
        super.onStop();
        Uteis.MSG(getApplicationContext(), "onStop");
        Texto_to_Speak("On Stop");
    }

    //------------------------------------------------------
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        Uteis.MSG(getApplicationContext(), "onDestroy");
        Texto_to_Speak("On Destroy");

        super.onDestroy();
        String Dados_clientes = "";
        for (int i = 0; i < 100; i++)
            Dados_clientes += ";Cliente_" + i + ";Peso="+(i+10);

        SharedPreferences.Editor Editor = Pref.edit();
        Editor.putInt("PONTUACAO", ++PONTUACAO);
        Editor.putString("MEDIA", "Media = "+MEDIA);
        Editor.putString("CLIENTES", Dados_clientes);
        Editor.commit();

    }

    //------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        Uteis.MSG(getApplicationContext(), "onPause");
        Texto_to_Speak("On Pause");
    }

    //------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        Uteis.MSG(getApplicationContext(), "onResume");
        Uteis.MSG(getApplicationContext(), "PONTUACAO = " + PONTUACAO);
        Texto_to_Speak("On Resume");
    }

    //------------------------------------------------------
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Uteis.MSG(getApplicationContext(), "onBackPressed");
        Texto_to_Speak("On Back");
    }
    //------------------------------------------------------
    //------------------------------------------------------
    //------------------------------------------------------

}
