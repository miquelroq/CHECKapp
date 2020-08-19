package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.StackedBarModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JanelaGraficos extends AppCompatActivity implements View.OnClickListener
{
    ValueLineChart mCubicValueLineChart;
    BarChart mBarChart;
    PieChart mPieChart;
    StackedBarChart mStackedBarChart;
    Random Aleatorio;
    //------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_graficos);
        Uteis.MSG(getApplicationContext(), "JanelaGrafico:onCreate");

        Aleatorio = new Random();
        findViewById(R.id.Btn_Clear).setOnClickListener(this);
        findViewById(R.id.Btn_Mostrar_Graficos).setOnClickListener(this);

        mostrarGrafico_ValueLineChart();
        mostrarGrafico_Barchart();
        mostrarGrafico_PieChart();
        mostrarGrafico_StackedBarChart();
    }
    //------------------------
    private void mostrarGrafico_Barchart() {
        mBarChart = (BarChart) findViewById(R.id.Grafico_Barchart);
        mBarChart.clearChart();
        mBarChart.addBar(new BarModel(2.3f, 0xFF123456));
        mBarChart.addBar(new BarModel(2.f, 0xFF343456));
        mBarChart.addBar(new BarModel(3.3f, 0xFF563456));
        mBarChart.addBar(new BarModel(1.1f, 0xFF873F56));
        mBarChart.addBar(new BarModel(2.7f, 0xFF56B7F1));
        mBarChart.addBar(new BarModel(2.f, 0xFF343456));
        mBarChart.addBar(new BarModel(0.4f, 0xFF1FF4AC));
        mBarChart.addBar(new BarModel(4.f, 0xFF1BA4E6));

        mBarChart.startAnimation();
    }
    //------------------------
    private void mostrarGrafico_ValueLineChart() {
        mCubicValueLineChart = (ValueLineChart) findViewById(R.id.Grafico_ValueLineChart);
        mCubicValueLineChart.clearChart();
        mCubicValueLineChart.setLineStroke(0.2f);
        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFFFF3366);
        //series.setColor(0xFF56B7F1);

        series.addPoint(new ValueLinePoint("", 0f));
        series.addPoint(new ValueLinePoint("Dom", 2.4f));
        series.addPoint(new ValueLinePoint("Seg", 3.4f));
        series.addPoint(new ValueLinePoint("Ter", .4f));
        series.addPoint(new ValueLinePoint("Qua", 1.2f));
        series.addPoint(new ValueLinePoint("Qui", 10.6f));
        series.addPoint(new ValueLinePoint("Sex", 1.0f));
        series.addPoint(new ValueLinePoint("Sab", 3.5f));
        series.addPoint(new ValueLinePoint("", 0f));

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }
    //------------------------
    private void mostrarGrafico_PieChart() {
        mPieChart = (PieChart) findViewById(R.id.Grafico_PieChart);
        mPieChart.clearChart();
        mPieChart.addPieSlice(new PieModel(2.3f, 0xFF123456));
        mPieChart.addPieSlice(new PieModel(2.3f, 0xFF123456));
        mPieChart.addPieSlice(new PieModel(2.f, 0xFF343456));
        mPieChart.addPieSlice(new PieModel(3.3f, 0xFF563456));
        mPieChart.addPieSlice(new PieModel(1.1f, 0xFF873F56));
        mPieChart.addPieSlice(new PieModel(2.7f, 0xFF56B7F1));
        mPieChart.addPieSlice(new PieModel(2.f, 0xFF343456));
        mPieChart.addPieSlice(new PieModel(0.4f, 0xFF1FF4AC));
        mPieChart.addPieSlice(new PieModel(4.f, 0xFF1BA4E6));
        mPieChart.startAnimation();
    }
    //------------------------
    private void mostrarGrafico_StackedBarChart() {
        mStackedBarChart = (StackedBarChart) findViewById(R.id.Grafico_StackedBarChart);
        mStackedBarChart.clearChart();
        int NEntradas = 5 + Aleatorio.nextInt(6);
        for (int i = 0; i < NEntradas; i++) {
            int NDados = 2 + Aleatorio.nextInt(3);
            List<BarModel> lx = new ArrayList<>();
            for (int j = 0; j < NDados; j++) {
                int RED = Aleatorio.nextInt(255);
                int GREEN = Aleatorio.nextInt(255);
                int BLUE = Aleatorio.nextInt(255);
                lx.add(new BarModel(Aleatorio.nextInt(20) / 5.0f, Color.rgb(RED, GREEN, BLUE)));
            }
            StackedBarModel x = new StackedBarModel("L"+i, lx);
            mStackedBarChart.addBar(x);
        }
        mStackedBarChart.startAnimation();
    }
    //------------------------
    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.Btn_Clear:
                mCubicValueLineChart.clearChart();
                mBarChart.clearChart();
                mPieChart.clearChart();
                mStackedBarChart.clearChart();

                mCubicValueLineChart.startAnimation();
                mBarChart.startAnimation();
                mPieChart.startAnimation();
                mStackedBarChart.startAnimation();
                break;
            case R.id.Btn_Mostrar_Graficos:
                mostrarGrafico_ValueLineChart();
                mostrarGrafico_Barchart();
                mostrarGrafico_PieChart();
                mostrarGrafico_StackedBarChart();
                break;
        }
    }
    //------------------------
    //------------------------
    //------------------------
    //------------------------
    //------------------------
}
