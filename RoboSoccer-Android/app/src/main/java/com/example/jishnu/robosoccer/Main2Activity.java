package com.example.jishnu.robosoccer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Main2Activity extends AppCompatActivity {

    BluetoothAdapter BA;
    ListView listView;
    ArrayAdapter adapter;
    BroadcastReceiver broadcastReceiver;
    Set<BluetoothDevice> pairedDevice;
    BluetoothDevice foundDevice,mDevice;
    Button scan;
    String address;

    static BluetoothSocket bluetoothSocket;
    static UUID uuid;
    static OutputStream bOutputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView=(ListView)findViewById(R.id.listView);
        scan=(Button)findViewById(R.id.buttonScan);
        BA= BluetoothAdapter.getDefaultAdapter();
        if(getIntent().getStringExtra("from").equals("show"))
            showPairedDevice();
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pair device first",Toast.LENGTH_LONG).show();

            }


        });
    }

    void showPairedDevice() {
        pairedDevice = BA.getBondedDevices();
        this.setTitle("Paired Devices");
        scan = (Button) findViewById(R.id.buttonScan);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        for (BluetoothDevice device : pairedDevice) {
            adapter.add(device.getName() + "\n" + device.getAddress());
        }
        listView.setAdapter(adapter);
        //pairedList.setVisibility(View.VISIBLE);
        scan.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView) view).getText().toString();
                address = item.substring(item.length() - 17, item.length());
                connectDevice(address);

            }
        });

    }


    void connectDevice(String remoteAddress )
    {
        mDevice=BA.getRemoteDevice(remoteAddress);

        uuid=UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        try
        {
            bluetoothSocket=mDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();
            bOutputStream=bluetoothSocket.getOutputStream();

        }
        catch(Exception E)
        {
            Toast.makeText(getApplicationContext(),E.getMessage(),Toast.LENGTH_LONG).show();
        }
        if(bluetoothSocket.isConnected())
        {

            Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_LONG).show();
            BA.cancelDiscovery();
            Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
            intent.putExtra("address",address);
            startActivity(intent);
        }

    }




}


