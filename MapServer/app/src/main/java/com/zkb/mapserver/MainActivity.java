package com.zkb.mapserver;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zkb.mapserver.util.TTSController;

public class MainActivity extends AppCompatActivity {

    private TTSController mTtsManager;
    private TextView mText;
    private Handler handler=new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            mText.setText("接收到文字："+msg.obj.toString());
            Toast.makeText(getApplicationContext(),msg.obj.toString(),Toast.LENGTH_LONG).show();
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText= (TextView) findViewById(R.id.text);

        mTtsManager = TTSController.getInstance(getApplicationContext());
        mTtsManager.init();
        mTtsManager.startSpeaking("启动服务器中....");
        Thread serverThread=new ServerThread();
        serverThread.start();
    }

    class  ServerThread extends Thread{

        @Override
        public void run() {
            super.run();
            UDPServer udpServer=new UDPServer();
            udpServer.server(new UDPServer.CallBack() {
                @Override
                public void msg(String msg) {

                    Log.d("mytest","server-------------=="+msg);
                    Message message=handler.obtainMessage();
                    message.obj=msg;
                    handler.sendMessage(message);
                    mTtsManager.startSpeaking(msg);
                }
            });
        }
    }
}
