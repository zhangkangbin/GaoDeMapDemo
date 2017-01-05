package com.zkb.gaodemap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GuideActivity extends AppCompatActivity {

    private EditText editText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
         editText= (EditText) findViewById(R.id.edt_ip);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ip=editText.getText().toString();
                if(!TextUtils.isEmpty(ip)){
                    Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                    intent.putExtra("IP",ip);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"ip地址不能为空",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
