package com.example.jishnu.robosoccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class Main3Activity extends AppCompatActivity {
    Button front,back,left,right,stop;
    int fflag,bflag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        front=(Button) findViewById(R.id.front);
        back=(Button) findViewById(R.id.back);
        right=(Button) findViewById(R.id.right);
        left=(Button) findViewById(R.id.left);
        stop=(Button) findViewById(R.id.stop);
        fflag=0;
        bflag=0;

        front.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    try {
                        Main2Activity. bOutputStream.write("F".getBytes());
                        fflag=1;
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        Main2Activity. bOutputStream.write("S".getBytes());
                        fflag=0;
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    try {
                        Main2Activity. bOutputStream.write("B".getBytes());
                        bflag=1;
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        Main2Activity. bOutputStream.write("S".getBytes());
                        bflag=0;
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    try {
                        Main2Activity. bOutputStream.write("R".getBytes());
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        Main2Activity. bOutputStream.write("S".getBytes());
                        if (fflag==1){
                            Main2Activity. bOutputStream.write("F".getBytes());
                        }
                        else if (bflag==1){
                            Main2Activity. bOutputStream.write("B".getBytes());
                        }
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    try {
                        Main2Activity. bOutputStream.write("L".getBytes());

                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        Main2Activity. bOutputStream.write("S".getBytes());

                        if(fflag==1){
                            Main2Activity. bOutputStream.write("F".getBytes());
                        }
                        else if(bflag==1){
                            Main2Activity. bOutputStream.write("B".getBytes());
                        }
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Main2Activity. bOutputStream.write("H".getBytes());

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Error in sending",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        try {
            Main2Activity.bluetoothSocket.close();
        }catch(Exception e){}
        Toast.makeText(getApplicationContext(),"Device is disconnected",Toast.LENGTH_LONG).show();
        this.finish();
    }
}
