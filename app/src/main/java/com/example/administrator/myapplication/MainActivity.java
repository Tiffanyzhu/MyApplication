package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myapplication.service.BluetoothService2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView_hostogram, textView_bluetooth, textView_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_hostogram = (TextView) findViewById(R.id.textView_hostogram);
        textView_bluetooth = (TextView) findViewById(R.id.textView_bluetooth);
        textView_video = (TextView) findViewById(R.id.textView_video);
        textView_hostogram.setOnClickListener(this);
        textView_bluetooth.setOnClickListener(this);
        textView_video.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView_hostogram:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        HistogramViewActivity.class);
                startActivity(intent);
                break;

            case R.id.textView_bluetooth:
//                Intent intent2 = new Intent();
//                intent2.setClass(MainActivity.this,
//                        BluetoothActivity.class);
//                startActivity(intent2);
                startService(new Intent(this, BluetoothService2.class));
                break;
            case R.id.textView_video:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this,
                        VideoPlayerActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
