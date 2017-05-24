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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class squad extends AppCompatActivity {

    private MyFTPClientFunctions ftpclient = null;
    public String array_alineacion = null;
    public String[] elems_squads = null;
    List<String> local_por = new ArrayList<String>();
    List<String> local_def = new ArrayList<String>();
    List<String> local_med = new ArrayList<String>();
    List<String> local_del = new ArrayList<String>();
    List<String> vis_por = new ArrayList<String>();
    List<String> vis_def = new ArrayList<String>();
    List<String> vis_med = new ArrayList<String>();
    List<String> vis_del = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad);

        //Declaracion de los Buttons
        Button downloadButton = (Button) findViewById(R.id.download);
        final TextView l_team = (TextView) findViewById(R.id.local_team);
        final TextView l_por = (TextView) findViewById(R.id.l_l_por);
        final TextView l_def = (TextView) findViewById(R.id.l_l_def);
        final TextView l_med = (TextView) findViewById(R.id.l_l_med);
        final TextView l_del = (TextView) findViewById(R.id.l_l_del);
        final TextView v_team = (TextView) findViewById(R.id.v_team);
        final TextView v_por = (TextView) findViewById(R.id.v_l_por);
        final TextView v_def = (TextView) findViewById(R.id.v_l_def);
        final TextView v_med = (TextView) findViewById(R.id.v_l_med);
        final TextView v_del = (TextView) findViewById(R.id.v_l_del);


        final String array_alineaciones;
        ftpclient = new MyFTPClientFunctions();


        downloadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                Thread t = new Thread(new Runnable() {
                    public void run() {
                        array_alineacion = ftpclient.ftpDownload("ftp.sfacilcb.pe.hu", "u843056452", "x7evki_", 21,"alineaciones.txt");


                    }
                });t.start();

                try {
                    t.join();  // wait for thread to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                String[] elems_squads = array_alineacion.split(" ");

                //Como el formato del fichero del servidor es siempre
                //NOMBRE LOCAL - POSICION JUGADOR (asi con todos los locales) - NOMBRE VISITANTE - POSICION- JUGaDOR;
                //Y siempre hay 11 jugadores, el elemento 0 i el 23 siempre seran los nombres de los equipos

                //Las posiciones impares son las posiciones de los jugadores
                //Las pares los jugadores.
                for (int i = 1; i < 22; i += 2){
                    switch(elems_squads[i])
                    {
                        case "P" :
                            local_por.add(elems_squads[i+1]);
                            break;
                        case "D" :
                            local_def.add(elems_squads[i+1]);
                            break;
                        case "M" :
                            local_med.add(elems_squads[i+1]);
                            break;
                        case "F" :
                            local_del.add(elems_squads[i+1]);
                        default :
                    }
                }

                //Conversion a String para bolcarlo por TextView

                String t_local_por = local_por.toString();
                String t_local_def = local_def.toString();
                String t_local_med = local_med.toString();
                String t_local_del = local_del.toString();

                String tt_local_por = t_local_por.substring(1,t_local_por.length()-1);
                String tt_local_def = t_local_def.substring(1,t_local_def.length()-1);
                String tt_local_med = t_local_med.substring(1,t_local_med.length()-1);
                String tt_local_del = t_local_del.substring(1,t_local_del.length()-1);

                //Igual para los visitantes pero ara pos son pares i jugadores impares emepzando desde la 24
                for (int i = 24; i < elems_squads.length; i += 2){
                    switch(elems_squads[i])
                    {
                        case "P" :
                            vis_por.add(elems_squads[i+1]);
                            break;
                        case "D" :
                            vis_def.add(elems_squads[i+1]);
                            break;
                        case "M" :
                            vis_med.add(elems_squads[i+1]);
                            break;
                        case "F" :
                            vis_del.add(elems_squads[i+1]);
                        default :
                    }
                }

                //Conversion a String para bolcarlo por TextView

                String t_vis_por = vis_por.toString();
                String t_vis_def = vis_def.toString();
                String t_vis_med = vis_med.toString();
                String t_vis_del = vis_del.toString();

                String tt_vis_por = t_vis_por.substring(1,t_vis_por.length()-1);
                String tt_vis_def = t_vis_def.substring(1,t_vis_def.length()-1);
                String tt_vis_med = t_vis_med.substring(1,t_vis_med.length()-1);
                String tt_vis_del = t_vis_del.substring(1,t_vis_del.length()-1);


                l_team.setText(elems_squads[0]);
                l_por.setText(tt_local_por);
                l_def.setText(tt_local_def);
                l_med.setText(tt_local_med);
                l_del.setText(tt_local_del);
                v_team.setText(elems_squads[23]);
                v_por.setText(tt_vis_por);
                v_def.setText(tt_vis_def);
                v_med.setText(tt_vis_med);
                v_del.setText(tt_vis_del);

                //(!) Y limpiamos los ArrayList que habiamos hecho los add y se quedan

                local_por.clear();
                local_def.clear();
                local_med.clear();
                local_del.clear();
                vis_por.clear();
                vis_def.clear();
                vis_med.clear();
                vis_del.clear();
            }
        });
        

    }
}
