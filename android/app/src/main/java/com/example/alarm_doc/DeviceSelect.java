package com.example.alarm_doc;

import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarm_doc.services.BitalinoCapture;

import java.util.ArrayList;

import info.plux.pluxapi.BTHDeviceScan;
import info.plux.pluxapi.Constants;

/**
 *
 * Scan Bluetooth devices
 * When one of them is picked, BitalinoCapture is started
 * When Bitalino Capture is finished, the Test activity will catch the broadcast data
 *
 * Connect to BTH via Bluetooth
 * Collect BTH data
 *
 */
public class DeviceSelect extends ListActivity {

    private int bitalinoNumber = 1;

    private DeviceListAdapter deviceListAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;

    private BTHDeviceScan bthDeviceScan;
    private boolean isScanDevicesUpdateReceiverRegistered = false;

    private static final int REQUEST_ENABLE_BT = 1;


    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkup);
        mHandler = new Handler();

        registerReceiver(scanDevicesUpdateReceiver, new IntentFilter(Constants.ACTION_MESSAGE_SCAN));

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        bitalinoNumber = bundle.getInt("bitalinoNumber", 1);

        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        assert bluetoothManager != null;
        mBluetoothAdapter = bluetoothManager.getAdapter();

        bthDeviceScan = new BTHDeviceScan(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dItem = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        scanDevice(true);
    }

    private final BroadcastReceiver scanDevicesUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            assert action != null;
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if(bluetoothDevice != null){
                    deviceListAdapter.addDevice(bluetoothDevice);
                    deviceListAdapter.notifyDataSetChanged();
                }

            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void scanDevice(final boolean enable) {
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

        Toast.makeText(this, "ola", Toast.LENGTH_SHORT).show();

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

    // Adapter for holding devices found through scanning.
    private class DeviceListAdapter extends BaseAdapter {
        private ArrayList<BluetoothDevice> devices;
        private LayoutInflater mInflator;

        public DeviceListAdapter() {
            super();
            devices = new ArrayList<>();
            mInflator = DeviceSelect.this.getLayoutInflater();
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
            final ViewHolder viewHolder;
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

            viewHolder.deviceName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            // Start the BITalino capture and pass on the address as extra (todo)
                            Intent bitCapture = new Intent(DeviceSelect.this, BitalinoCapture.class);
                            startService(bitCapture);
                        }
                    });



                    // Launch the animated testing activity
                    Intent dataProcessing = new Intent(DeviceSelect.this, DataProcessing.class);
                    startActivity(dataProcessing);


                }
            });

            return view;
        }
    }

    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }



}
