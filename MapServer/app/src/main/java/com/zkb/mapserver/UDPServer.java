package com.zkb.mapserver;

/**
 * Created by zhangkangbin on 2017/1/5.
 */


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


import android.content.Context;
import android.util.Log;

import java.io.*;
import java.net.*;

public class UDPServer {

    ServerSocket serverSocket = null;

    public void server(CallBack callBack) {


        try {
            serverSocket = new ServerSocket(4700);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("mytest", "serverSocket = new ServerSocket(4700)");
        Socket clientSocket = null;
        while (true) {
            //循环监听客户端请求
            Log.d("mytest", "/循环监听客户端请求");
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("mytest", "/Receiving");
            System.out.println("Server: Receiving...");
            try {
                //获取输入流
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                //获取从客户端发来的信息
                String str = in.readLine();
                if (callBack != null) {
                    callBack.msg(str);
                }
                Log.d("mytest", "Server:    Received: '" + str + "'");
            } catch (Exception e) {
                System.out.println("Server: Error");
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Server: Close.");
            }
        }

    }

    interface CallBack {

        void msg(String msg);
    }
}