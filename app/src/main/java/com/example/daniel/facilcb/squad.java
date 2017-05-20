package com.example.daniel.facilcb;

import static android.content.ContentValues.TAG;
import org.apache.commons.net.ftp.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class squad extends AppCompatActivity {

    private MyFTPClientFunctions ftpclient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad);

        //Declaracion de los Buttons
        Button connectButton = (Button) findViewById(R.id.connect);
        Button downloadButton = (Button) findViewById(R.id.download);
        Button disconnectButton = (Button) findViewById(R.id.disconnect);

        ftpclient = new MyFTPClientFunctions();

        connectButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new Thread(new Runnable() {
                    public void run() {
                        boolean status = false;
                        status = ftpclient.ftpConnect("ftp.sfacilcb.pe.hu", "u843056452", "x7evki_", 21);
                        if (status == true) {
                            Log.d(TAG, "Connection Success");
                        } else {
                            Log.d(TAG, "Connection failed");
                        }
                    }
                }).start();
            }
        });

        disconnectButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new Thread(new Runnable() {
                    public void run() {
                        boolean status = false;
                        status = ftpclient.ftpDisconnect();

                        if (status == true) {
                            Log.d(TAG, "EXIT Success");
                        } else {
                            Log.d(TAG, "EXIT failed");
                        }
                    }
                }).start();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new Thread(new Runnable() {
                    public void run() {
                        boolean status = false;
                        status = ftpclient.ftpDownload("/alineaciones.txt","/res");

                        if (status == true) {
                            Log.d(TAG, "Download Success");
                        } else {
                            Log.d(TAG, "AAAA Download failed");
                        }
                    }
                }).start();
            }
        });




    }
}
