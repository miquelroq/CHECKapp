package com.example.alarm_doc;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Timer;

import info.plux.pluxapi.BTHDeviceScan;
import info.plux.pluxapi.Constants;

/**
 *
 * Running the diagnostic
 *
 * Connect to BTH via Bluetooth
 * Collect BTH data
 * Process signals in order to extract the most relevant features to be sent to the API
 * Send to the API
 * GET the response
 * Launch activity according to the response/outcome
 *
 *
 */

// TODO:
//      * Connect to BITalino via Bluetooth
//      * Collect BITalino data and store it in a HashMap or ArrayLists of ints
//      * Send the collected data to Django API
//      * Wait for the response
//      * Launch Healthy/Unhealthy activity according to the received response

public class Checkup extends ListActivity {

    private DeviceListAdapter deviceListAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;

    private BTHDeviceScan bthDeviceScan;
    private boolean isScanDevicesUpdateReceiverRegistered = false;

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 2;

    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkup);
        mHandler = new Handler();

        double test_outcome = Math.random();
        final Intent intent;
        int fatigue, chills;
        Timer timer = new Timer();

        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Checks if Bluetooth is supported on the device.
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Error - Bluetooth not supported", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        permissionCheck();

        bthDeviceScan = new BTHDeviceScan(this);

        // Collect the received dictionary
        fatigue = getIntent().getIntExtra("fatigue", 0);
        chills = getIntent().getIntExtra("chills", 0);

        // Starting Datagen in the background when this activity is created
        Intent service = new Intent(this, DataGenerator.class);
        // startService(service);

        scanDevice(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        if (!mScanning) {
            menu.findItem(R.id.menu_stop).setVisible(false);
            menu.findItem(R.id.menu_scan).setVisible(true);
            menu.findItem(R.id.menu_refresh).setActionView(null);
        } else {
            menu.findItem(R.id.menu_stop).setVisible(true);
            menu.findItem(R.id.menu_scan).setVisible(false);
            menu.findItem(R.id.menu_refresh).setActionView(R.layout.actionbar_indeterminate_progress);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                deviceListAdapter.clear();
                scanDevice(true);
                break;
            case R.id.menu_stop:
                scanDevice(false);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(scanDevicesUpdateReceiver, intentFilter);
        isScanDevicesUpdateReceiverRegistered = true;

        // Ensures Bluetooth is enabled on the device.  If Bluetooth is not currently enabled,
        // fire an intent to display a dialog asking the user to grant permission to enable it.
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }

        // Initializes list view adapter.
        deviceListAdapter = new DeviceListAdapter();
        setListAdapter(deviceListAdapter);
        scanDevice(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("banana", "coarse location permission granted");
                }
                else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle(getString(R.string.permission_denied_dialog_title))
                            .setMessage(getString(R.string.permission_denied_dialog_message))
                            .setPositiveButton(getString(R.string.permission_denied_dialog_positive_button), null)
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {

                                }
                            });
                    builder.show();
                }
                break;
            default:
                return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanDevice(false);
        deviceListAdapter.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(bthDeviceScan != null){
            bthDeviceScan.closeScanReceiver();
        }

        if(isScanDevicesUpdateReceiverRegistered){
            unregisterReceiver(scanDevicesUpdateReceiver);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
/*        final BluetoothDevice device = deviceListAdapter.getDevice(position);
        if (device == null) return;
        final Intent intent = new Intent(this, DeviceActivity.class);
        intent.putExtra(DeviceActivity.EXTRA_DEVICE, device);
        if (mScanning) {
            bthDeviceScan.stopScan();
            mScanning = false;
        }
        startActivity(intent);*/
        Log.d("banana", "olaolaoalaoal");
    }

    private void scanDevice(final boolean enable) {

        Toast.makeText(this, "scanning", Toast.LENGTH_SHORT).show();
        
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    bthDeviceScan.stopScan();
                    invalidateOptionsMenu();
                }
            }, SCAN_PERIOD);

            mScanning = true;
            bthDeviceScan.doDiscovery();
        } else {
            mScanning = false;
            bthDeviceScan.stopScan();
        }
        invalidateOptionsMenu();
    }

    private final BroadcastReceiver scanDevicesUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Constants.ACTION_MESSAGE_SCAN)){
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(Constants.EXTRA_DEVICE_SCAN);

                if(bluetoothDevice != null){
                    deviceListAdapter.addDevice(bluetoothDevice);
                    deviceListAdapter.notifyDataSetChanged();
                }
            }
        }
    };

    private void permissionCheck(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Android Marshmallow and above permission check
            if(this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.permission_check_dialog_title))
                        .setMessage(getString(R.string.permission_check_dialog_message))
                        .setPositiveButton(getString(R.string.permission_check_dialog_positive_button), null)
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                            }
                        });
                builder.show();
            }
        }
    }

    // Adapter for holding devices found through scanning.
    private class DeviceListAdapter extends BaseAdapter {
        private ArrayList<BluetoothDevice> devices;
        private LayoutInflater mInflator;

        public DeviceListAdapter() {
            super();
            devices = new ArrayList<>();
            mInflator = Checkup.this.getLayoutInflater();
        }

        public void addDevice(BluetoothDevice device) {
            if(!devices.contains(device)) {
                devices.add(device);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return devices.get(position);
        }

        public void clear() {
            devices.clear();
        }

        @Override
        public int getCount() {
            return devices.size();
        }

        @Override
        public Object getItem(int i) {
            return devices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = mInflator.inflate(R.layout.listitem_device, null);
                viewHolder = new ViewHolder();
                viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
                viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            BluetoothDevice device = devices.get(i);
            final String deviceName = device.getName();
            if (deviceName != null && deviceName.length() > 0) {
                viewHolder.deviceName.setText(deviceName);
            }
            else {
                viewHolder.deviceName.setText("BITalino");
            }
            viewHolder.deviceAddress.setText(device.getAddress());

            return view;
        }


    }

    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }
}