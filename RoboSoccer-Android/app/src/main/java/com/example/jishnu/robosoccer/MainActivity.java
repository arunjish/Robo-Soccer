package com.example.jishnu.robosoccer;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button,disconnect,connect,visibleDevice;
    Button connectButton;
    BroadcastReceiver broadcastReceiver;
    BluetoothAdapter BA=BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButton=(Button)findViewById(R.id.connectButton);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });

    }


     void connect() {
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 3);
        } else {

            showPairedDevices();
        }
     }

     void disconnect() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure to want disconnect");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BA.disable();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
     }

     void showPairedDevices() {
        if (BA.isEnabled()) {

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("from", "show");
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth is Not on", Toast.LENGTH_LONG).show();
        }
     }





    }




