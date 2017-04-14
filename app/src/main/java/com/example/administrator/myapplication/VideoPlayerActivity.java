package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.administrator.myapplication.view.VideoView;

/**
 * @author lulizhu
 * @ClassName
 * @Description
 * @date
 */
public class VideoPlayerActivity extends Activity  implements SurfaceHolder.Callback{

    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private VideoView myView;
    private MediaPlayer mMediaPlayer;
     /*测试用播放视频*/
    private String strVideoPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        findViews();

    }

    private void findViews(){
        surfaceView=(SurfaceView) findViewById(R.id.video_chat_preview);
        surfaceHolder=surfaceView.getHolder();
        myView=(VideoView) findViewById(R.id.video_chat_myview);
       /* 获取SurfaceHolder控制 SurfaceView*/
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

       /*默认视频分辨率 176x144*/
        surfaceHolder.setFixedSize(176,144);
        //设置.....
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    getWindow().setAttributes(attrs);
                    getWindow().addFlags(
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    getWindow().setAttributes(attrs);
                    getWindow().clearFlags(
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                }
                return false;
            }
        });
//        playVideo(strVideoPath);

    }
    /*播放视频函数*/
    private void playVideo(String strPath)
    {

        //DisplayMetircs 类可以很方便的获取分辨率     获取窗口的分辨率
        //这里获取窗口的大小
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //设置Layout的参数
        ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
        lp.width = metrics.widthPixels/2;
        lp.height =metrics.heightPixels/2;
        surfaceView.setLayoutParams(lp);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


     /* */
        mMediaPlayer.setDisplay(surfaceHolder);

        try
        {
            mMediaPlayer.setDataSource(strPath);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
//            mMakeTextToast("setDataSource Exceeption:"+e.toString(),true);
            //mTextView01.setText("setDataSource Exceeption:"+e.toString());
            e.printStackTrace();
        }

        try
        {
            mMediaPlayer.prepare();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
//            mMakeTextToast("setDataSource Exceeption:"+e.toString(),true);
            //mTextView01.setText("prepare Exceeption:"+e.toString());
            e.printStackTrace();
        }

        mMediaPlayer.start();
//        bIsReleased = false;
        //mTextView01.setText(R.string.str_play);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer arg0)
            {
                // TODO Auto-generated method stub
//                mMakeTextToast("stop",true);
                //  mTextView01.setText(R.string.str_stop);

//                bIsPaused = true;//视频播放完毕了，就自动回到了0位置，并停止了。点击停止/继续 即可播放。
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //DisplayMetircs 类可以很方便的获取分辨率     获取窗口的分辨率
        //这里获取 窗口 的分辨率大小
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //设置Layout的参数
        ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
        lp.width = metrics.widthPixels/2;
        lp.height =metrics.heightPixels/2;
        surfaceView.setLayoutParams(lp);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
