package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class JanelaConfiguracoes extends AppCompatActivity implements View.OnClickListener
{
    Spinner spinnerSR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_configuracoes);

        Intent Act = getIntent();
        String EM = Act.getStringExtra("EMAIL_DEF");
        String TEL = Act.getStringExtra("TELEFONE");
        String LN = Act.getStringExtra("NOMES");

        Uteis.MSG(getApplicationContext(), LN);
        Uteis.MSG_DEBUG("ERROS", LN);

        EditText Ed = findViewById(R.id.EdTxt_EMAIL);
        Ed.setText(EM);

        findViewById(R.id.Btn_Enviar_Servidor).setOnClickListener(this);
        spinnerSR = (Spinner) findViewById(R.id.spinnerSamplingRate);
        String[] arraySpinner = new String[] { "100", "200", "300", "400", "500", "600", "700" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSR.setAdapter(adapter);
     }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Btn_Enviar_Servidor:
                Uteis.MSG(getApplicationContext(), "Enviar para o Servidor");
                EditText Ed = findViewById(R.id.EdTxt_EMAIL);
                Uteis.MSG(getApplicationContext(), "EMAIL: " + Ed.getText());
                break;
        }
    }
}
