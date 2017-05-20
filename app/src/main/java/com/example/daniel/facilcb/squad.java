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
    public String array_alineacion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad);

        //Declaracion de los Buttons
        Button downloadButton = (Button) findViewById(R.id.download);

        final String array_alineaciones;
        ftpclient = new MyFTPClientFunctions();


        downloadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new Thread(new Runnable() {
                    public void run() {
                        array_alineacion = ftpclient.ftpDownload("ftp.sfacilcb.pe.hu", "u843056452", "x7evki_", 21,"alineaciones.txt");
                        System.out.println(array_alineacion);
                    }
                }).start();
            }
        });
        

    }
}
