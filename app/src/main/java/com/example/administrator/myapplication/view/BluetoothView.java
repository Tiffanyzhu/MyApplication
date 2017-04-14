package com.example.administrator.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myapplication.BluetoothActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lulizhu
 * @ClassName
 * @Description
 * @date
 */
public class BluetoothView extends View {
    /*http://www.cnblogs.com/aibuli/p/950c34f2bc0d02cbd290dd6a8339d42a.html*/
    //坐标轴原点的位置
    private int xPoint=60;
    private int yPoint=260;
    //刻度长度
    private int xScale=8;  //8个单位构成一个刻度
    private int yScale=40;
    //x与y坐标轴的长度
    private int xLength=580;
    private int yLength=480;

    private int MaxDataSize=xLength/xScale;   //横坐标  最多可绘制的点

    private List<Float> data=new ArrayList<Float>();   //存放 纵坐标 所描绘的点

    private String[] yLabel=new String[yLength/yScale];  //Y轴的刻度上显示字的集合


    private Handler mh=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0){                //判断接受消息类型
                BluetoothView.this.invalidate();  //刷新View
            }
        };
    };
    public BluetoothView(Context context, AttributeSet attrs) {
        super(context, attrs);
        for (int i = 0; i <yLabel.length; i++) {
            yLabel[i]=(i+1)+"M/s";
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){     //在线程中不断往集合中增加数据
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(data.size()>MaxDataSize){  //判断集合的长度是否大于最大绘制长度
                        data.remove(0);  //删除头数据
                    }
                    // 这里得到蓝牙设备得到的数据
                    float[] floats = BluetoothActivity.GetAngleSpeed(1);
                    data.add(floats[0]);
                    mh.sendEmptyMessage(0);   //发送空消息通知刷新
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        //绘制Y轴
        canvas.drawLine(xPoint, yPoint-yLength, xPoint, yPoint, paint);
        //绘制Y轴左右两边的箭头
        canvas.drawLine(xPoint, yPoint-yLength, xPoint-3,yPoint-yLength+6, paint);
        canvas.drawLine(xPoint, yPoint-yLength, xPoint+3,yPoint-yLength+6, paint);
        //Y轴上的刻度与文字
        for (int i = 0; i * yScale< yLength; i++) {
            canvas.drawLine(xPoint, yPoint-i*yScale, xPoint+5, yPoint-i*yScale, paint);  //刻度
            canvas.drawText(yLabel[i], xPoint-50, yPoint-i*yScale, paint);//文字
        }
        //X轴
        canvas.drawLine(xPoint, yPoint, xPoint+xLength, yPoint, paint);
        //如果集合中有数据
        if(data.size()>1){
            for (int i = 1; i < data.size(); i++) {  //依次取出数据进行绘制
                canvas.drawLine(xPoint+(i-1)*xScale, yPoint-data.get(i-1)*yScale, xPoint+i*xScale, yPoint-data.get(i)*yScale, paint);
            }
        }

    }
}
