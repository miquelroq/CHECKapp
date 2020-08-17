package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JanelaGPS extends AppCompatActivity implements View.OnClickListener {
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; //em metros
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 5000; //em milissegundos
    TextView Tx_LAT, Tx_LNG;
    LocationManager locationManager;
    MyLocationListener myLocation;
    int Numero_Click_Onde_Estou;

    //class para gerir localizacao
    private class MyLocationListener implements LocationListener {
        private double latitude;
        private double longitude;

        public void onLocationChanged(Location loc) {
            latitude = loc.getLatitude();
            longitude = loc.getLongitude();
            String message = String.format("Localizacao\n Longitude: %1$s \n Latitude: %2$s", latitude, longitude);
            Uteis.MSG(getApplicationContext(), message);
            Tx_LAT.setText(String.valueOf(latitude));
            Tx_LNG.setText(String.valueOf(longitude));
        }

        public void onStatusChanged(String s, int i, Bundle b) {
            Uteis.MSG(getApplicationContext(), "Estado do provider alterado.");
        }

        public void onProviderDisabled(String s) {
            Uteis.MSG(getApplicationContext(), "GPS desligado");
        }

        public void onProviderEnabled(String s) {
            Uteis.MSG(getApplicationContext(), "GPS ligado");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_g_p_s);

        Numero_Click_Onde_Estou = 0;
        findViewById(R.id.Btn_Posicao_GPS).setOnClickListener(this);
        Tx_LAT = findViewById(R.id.tv_LAT);
        Tx_LNG = findViewById(R.id.tv_LNG);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myLocation = new MyLocationListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, myLocation);
    }

    protected void showCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        String message = String.format("Localizacaoo\n Longitude: %1$s \n Latitude: %2$s", location.getLongitude(), location.getLatitude());
        Uteis.MSG(getApplicationContext(), message);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.Btn_Posicao_GPS:
                Numero_Click_Onde_Estou++;
                if (Numero_Click_Onde_Estou == 1)
                    Configurar_GPS();
                else
                    Uteis.MSG(getApplicationContext(), "Calma!, já clicas-te " + Numero_Click_Onde_Estou + " Vezes Espera!, estou a processar!, Sê Paciente!!!!");
                break;
        }
    }

    public void Atualizar_GPS(Location location)
    {
        Double latPoint = location.getLatitude();
        Double lngPoint = location.getLongitude();

        Tx_LAT.setText(latPoint.toString());
        Tx_LNG.setText(lngPoint.toString());
    }
    public void Configurar_GPS()
    {
        try {
            Uteis.MSG(getApplicationContext(), "Configurar_GPS");
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (!gpsEnabled) {
                Uteis.MSG(getApplicationContext(), "gpsEnabled = " + gpsEnabled);
            }
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    Atualizar_GPS(location);
                }
                public void onStatusChanged(String provider, int status, Bundle extras) { }
                public void onProviderEnabled(String provider) { }
                public void onProviderDisabled(String provider) { }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        catch(SecurityException ex){
            Uteis.MSG(getApplicationContext(), ex.getMessage());
        }
    }
}
