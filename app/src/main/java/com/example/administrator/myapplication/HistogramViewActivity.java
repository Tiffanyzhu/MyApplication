package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.view.HistogramView;
import com.example.administrator.myapplication.view.HistogramView.Bar;
import java.util.ArrayList;

/**
 * @author lulizhu
 * @ClassName 柱状图
 * @Description
 * @date
 */
public class HistogramViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogramview);
        HistogramView dcv7 = (HistogramView) findViewById(R.id.dcv_7);
//        ArrayList<Bar> bar7Lists = new ArrayList<Bar>();
//        Bar bar1 = dcv7.new Bar(1, 0.3f, Color.parseColor("#b6bcc8"), "one", "30");
//        Bar bar2 = dcv7.new Bar(2, 0.65f, Color.parseColor("#ff2d65"), "two", "65");
//        Bar bar3 = dcv7.new Bar(3, 0.8f, Color.parseColor("#59bbfa"), "three", "80");
//        bar7Lists.add(bar1);
//        bar7Lists.add(bar2);
//        bar7Lists.add(bar3);
//        dcv7.setBarLists(bar7Lists);
    }
}
