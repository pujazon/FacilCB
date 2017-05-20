package com.example.daniel.facilcb;

import static android.content.ContentValues.TAG;
import org.apache.commons.net.ftp.*;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class squad extends AppCompatActivity {

    private MyFTPClientFunctions ftpclient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ftpclient = new MyFTPClientFunctions();

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

        new Thread(new Runnable() {
            public void run() {
                ftpclient.ftpDisconnect();
            }
        }).start();
    }
}