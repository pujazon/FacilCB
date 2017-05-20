package com.example.daniel.facilcb;

import android.content.Context;
import android.util.Log;
import org.apache.commons.net.ftp.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;


/**
 * Created by daniel on 20/05/17.
 */

public class MyFTPClientFunctions {

    public FTPClient mFTPClient = null;
    BufferedReader reader = null;
    String firstLine = null;


    // DESCARGAR DEL FTP SERVER

    /**
     * mFTPClient: FTP client connection object (see FTP connection example)
     * srcFilePath: path to the source file in FTP server desFilePath: path to
     * the destination file to be saved in sdcard
     */
    public String ftpDownload(String host, String username, String password, int port, String FILE) {
        boolean status = false;
        String fileContent=null;
        try{


            mFTPClient = new FTPClient(); //Conecta al Host
            mFTPClient.connect(host, port); //
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                status = mFTPClient.login(username, password); //User y pasword
                //Establecemos el tipo de transm ficheros
                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFTPClient.enterLocalPassiveMode();
            }

            if (status) System.out.println("Connection OK");

            InputStream iStream=mFTPClient.retrieveFileStream(FILE);
            BufferedInputStream bInf=new BufferedInputStream (iStream);

            int bytesRead;
            byte[] buffer=new byte[1024];
            while((bytesRead=bInf.read(buffer))!=-1)
            {
                fileContent=new String(buffer,0,bytesRead);

            }

            mFTPClient.logout();
            mFTPClient.disconnect();


        } catch (Exception e) {
            Log.d(TAG, "An error has ocuerred while connecting, reading or disconnecting");
        }

        return fileContent;
    }



    /*
    //ACTUALIZAR FTP

    public boolean ftpUpload(String srcFilePath, String desFileName, String desDirectory, Context context) {
        boolean status = false;
        try {
            FileInputStream srcFileStream = new FileInputStream(srcFilePath);
            // change working directory to the destination directory
            // if (ftpChangeDirectory(desDirectory)) {
            status = mFTPClient.storeFile(desFileName, srcFileStream);
            // }
            srcFileStream.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "upload failed: " + e);
        }
        return status;
    }
    */
}
