package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class JanelaGraficos extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_graficos);
        Uteis.MSG(getApplicationContext(), "JanelaGrafico:onCreate");

        findViewById(R.id.Btn_Clear).setOnClickListener(this);
        findViewById(R.id.Btn_Adicionar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.Btn_Clear:
                break;
            case R.id.Btn_Adicionar:
                break;
        }
    }
}
