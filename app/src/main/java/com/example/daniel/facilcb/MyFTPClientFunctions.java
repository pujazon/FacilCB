package com.example.daniel.facilcb;

import android.content.Context;
import android.util.Log;
import org.apache.commons.net.ftp.*;

import java.io.FileInputStream;

import static android.content.ContentValues.TAG;


/**
 * Created by daniel on 20/05/17.
 */

public class MyFTPClientFunctions {

    public FTPClient mFTPClient = null;

    //1. CONECTARSE FTP
    public boolean ftpConnect(String host, String username, String password, int port) {
        try {
            mFTPClient = new FTPClient(); //Conecta al Host
            mFTPClient.connect(host, port); //
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                boolean status = mFTPClient.login(username, password); //User y pasword
                //Establecemos el tipo de transm ficheros
                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFTPClient.enterLocalPassiveMode();
                return status;
            }
        } catch (Exception e) {
            Log.d(TAG, "Error: could not connect to host " + host);
        }
        return false;
    }

    //DESCONECTARSE FTP
    public boolean ftpDisconnect() {
        try {
            mFTPClient.logout();
            mFTPClient.disconnect();
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Error occurred while disconnecting from ftp server.");
        }
        return false;
    }

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
}
