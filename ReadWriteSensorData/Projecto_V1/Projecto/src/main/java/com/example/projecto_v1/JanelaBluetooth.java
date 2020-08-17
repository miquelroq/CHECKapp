package com.example.projecto_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bitalino.comm.BITalinoDevice;
import com.bitalino.comm.BITalinoException;

import java.util.ArrayList;
import java.util.Set;

// https://github.com/BITalinoWorld/android-example/tree/master/src/main

public class JanelaBluetooth extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    boolean mScanning;
    //LeDeviceListAdapter leDeviceListAdapter;
    BITalinoDevice bitalino;
    CheckBox checkBox_BLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_bluetooth);

        checkBox_BLE = findViewById(R.id.checkBox_BLE);
       // mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Uteis.MSG(getApplicationContext(), "JanelaBluetooth: PASSO - 1");
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Uteis.MSG(getApplicationContext(), "JanelaBluetooth: FEATURE_BLUETOOTH_LE Não Suportado!");
            checkBox_BLE.setChecked(false);
        }
        else {
            Uteis.MSG(getApplicationContext(), "JanelaBluetooth: FEATURE_BLUETOOTH_LE SUPORTADO!");
            checkBox_BLE.setChecked(true);
        }
/*uportado!");
        if(!mBluetoothAdapter.isEnabled()) mBluetoothAdapter.enable();

        mBluetoothAdapter.startDiscovery();
        Set<BluetoothDevice> pairedDeviceSet = mBluetoothAdapter.getBondedDevices();
        if (pairedDeviceSet.size() > 0)
        {
            Uteis.MSG(getApplicationContext(),"N. Elementos = ");
            for (BluetoothDevice device : pairedDeviceSet){
                Uteis.MSG(getApplicationContext(), device.getName() + "\n" + device.getAddress());
                Uteis.MSG_DEBUG("BLUE", device.getName() + "\n" + device.getAddress());
            }
        }
        else Uteis.MSG(getApplicationContext(),"No Paired Device.");
        //Funcao_BLE();
        int res_start_bitalino = start_bitalino();
        Uteis.MSG(getApplicationContext(), "res_start_bitalino = " + res_start_bitalino);
        */
        String[] myDeviceList = this.getBluetoothDevices();
        for (String S : myDeviceList){
            Uteis.MSG(getApplicationContext(), "S:"  +S);
            Uteis.MSG_DEBUG("BLUETOOTH", "S:"  +S);
        }
    }
    private static final int REQUEST_ENABLE_BT = 12;
    private String[] getBluetoothDevices(){
        String[] result = null;
        ArrayList<String> devices = new ArrayList<String>();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()){
            Log.e("Dialog", "Couldn't find enabled the mBluetoothAdapter");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }else{
            Set<BluetoothDevice> devList = mBluetoothAdapter.getBondedDevices();

            for( BluetoothDevice device : devList)
                devices.add(device.getName() + "-"+ device.getAddress());

            String[] aux_items = new String[devices.size()];
            final String[] items = devices.toArray(aux_items);
            result = items;
        }
        return result;

    }
    //------------------------------------------------------
    // https://github.com/DavidGMarquez/Bitadroid/tree/master/Bplux/src/com/bitalino/comm
    public int start_bitalino()
    {
        try
        {
            bitalino = new BITalinoDevice(100, new int[]{0, 1, 2, 3, 4, 5});
            bitalino.start();
        }
        catch (BITalinoException e)
        {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    public int stop_bitalino(){
        try {
            bitalino.stop();
        } catch (BITalinoException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    //------------------------------------------------------
    private void Funcao_BLE()
    {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        scanLeDevice();
    }
    //------------------------------------------------------
    private void scanLeDevice()
    {
        Handler handler = new Handler();
        long SCAN_PERIOD = 10000;
        final BluetoothLeScanner bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        if (!mScanning) {
            // Stops scanning after a pre-defined scan period.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    bluetoothLeScanner.stopScan(leScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            bluetoothLeScanner.startScan(leScanCallback);
        } else {
            mScanning = false;
            bluetoothLeScanner.stopScan(leScanCallback);
        }
    }
    //------------------------------------------------------
    private ScanCallback leScanCallback =
            new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    Uteis.MSG(getApplicationContext(), result.getDevice().toString());
                    /*
                    leDeviceListAdapter.addDevice(result.getDevice());
                    leDeviceListAdapter.notifyDataSetChanged();
                    */
                }
            };
    //------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        Uteis.MSG(getApplicationContext(), "JanelaBluetooth: onStart");
    }

    //------------------------------------------------------
    @Override
    protected void onStop() {
        super.onStop();
        Uteis.MSG(getApplicationContext(), "JanelaBluetooth: onStop");
    }

    //------------------------------------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Uteis.MSG(getApplicationContext(), "JanelaBluetooth: onDestroy");
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
        }
    }

    //------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        Uteis.MSG(getApplicationContext(), "JanelaBluetooth: onPause");
    }

    //------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        Uteis.MSG(getApplicationContext(), "JanelaBluetooth: onResume");
        Uteis.MSG(getApplicationContext(), "JanelaBluetooth: PASSO - 1");
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Uteis.MSG(getApplicationContext(), "JanelaBluetooth: FEATURE_BLUETOOTH_LE Não Suportado!");
            checkBox_BLE.setEnabled(false);
        }
        else {
            Uteis.MSG(getApplicationContext(), "JanelaBluetooth: FEATURE_BLUETOOTH_LE SUPORTADO!");
            checkBox_BLE.setEnabled(true);
        }
    }
    //------------------------------------------------------
    //------------------------------------------------------
    //------------------------------------------------------
    //------------------------------------------------------
    //------------------------------------------------------
}
