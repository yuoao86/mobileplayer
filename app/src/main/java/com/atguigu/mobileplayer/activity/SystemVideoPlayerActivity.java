package com.atguigu.mobileplayer.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.utils.Utils;

public class SystemVideoPlayerActivity extends Activity implements View.OnClickListener {

    private static final int VIDEO_PROGRESS = 1;
    private VideoView videoView;


    private Utils utils;

    private LinearLayout llTop;
    private TextView tvName;
    private ImageView ivBattery;
    private TextView tvSystemTime;
    private ImageButton btVoice;
    private SeekBar seekbarVoice;
    private ImageButton btSwitchPlayer;
    private LinearLayout llBottom;
    private TextView tvCurrentTime;
    private SeekBar seekbarVideo;
    private TextView tvTotalTime;
    private ImageButton btExit;
    private ImageButton btPre;
    private ImageButton btVideoStartPause;
    private ImageButton btNext;
    private ImageButton btnVideoSiwchScreen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        setListener();
        utils=new Utils();

        Uri data = getIntent().getData();
        if (data != null) {
            videoView.setVideoURI(data);
        }
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case VIDEO_PROGRESS:
                    int currentPosition=videoView.getCurrentPosition();
                    seekbarVideo.setProgress(currentPosition);


                    int currentTime=videoView.getCurrentPosition();
                    tvCurrentTime.setText(utils.stringForTime(currentTime));

                    handler.removeMessages(VIDEO_PROGRESS);

                    handler.sendEmptyMessageDelayed(VIDEO_PROGRESS,1000);



                    break;
            }

        }
    };

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-08-06 11:54:37 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        setContentView(R.layout.activity_system_video_player);
        videoView = (VideoView)findViewById( R.id.videoView );
        llTop = (LinearLayout)findViewById( R.id.ll_top );
        tvName = (TextView)findViewById( R.id.tv_name );
        ivBattery = (ImageView)findViewById( R.id.iv_battery );
        tvSystemTime = (TextView)findViewById( R.id.tv_system_time );
        btVoice = (ImageButton)findViewById( R.id.bt_voice );
        seekbarVoice = (SeekBar)findViewById( R.id.seekbar_voice );
        btSwitchPlayer = (ImageButton)findViewById( R.id.bt_switch_player );
        llBottom = (LinearLayout)findViewById( R.id.ll_bottom );
        tvCurrentTime = (TextView)findViewById( R.id.tv_current_time );
        seekbarVideo = (SeekBar)findViewById( R.id.seekbar_video );
        tvTotalTime = (TextView)findViewById( R.id.tv_total_time );
        btExit = (ImageButton)findViewById( R.id.bt_exit );
        btPre = (ImageButton)findViewById( R.id.bt_pre );
        btVideoStartPause = (ImageButton)findViewById( R.id.bt_video_start_pause );
        btNext = (ImageButton)findViewById( R.id.bt_next );
        btnVideoSiwchScreen = (ImageButton)findViewById( R.id.btn_video_siwch_screen );

        btVoice.setOnClickListener( this );
        btSwitchPlayer.setOnClickListener( this );
        btExit.setOnClickListener( this );
        btPre.setOnClickListener( this );
        btVideoStartPause.setOnClickListener( this );
        btNext.setOnClickListener( this );
        btnVideoSiwchScreen.setOnClickListener( this );
    }


    private void setListener() {
        videoView.setOnPreparedListener(new MyOnPreparedListener());
        videoView.setOnErrorListener(new MyonErrorListener());
        videoView.setOnCompletionListener(new MyOnComletionListener());
        seekbarVideo.setOnSeekBarChangeListener(new VideoSeekBarChangeListener());
    }


    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-08-06 11:54:37 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btVoice ) {
            // Handle clicks for btVoice
        } else if ( v == btSwitchPlayer ) {
            // Handle clicks for btSwitchPlayer
        } else if ( v == btExit ) {
            // Handle clicks for btExit
        } else if ( v == btPre ) {
            // Handle clicks for btPre
        } else if ( v == btVideoStartPause ) {
            // Handle clicks for btVideoStartPause
            if (videoView.isPlaying()){
                videoView.pause();
                btVideoStartPause.setImageResource(R.drawable.btn_start_selector);
            }else{
                videoView.start();
                btVideoStartPause.setImageResource(R.drawable.btn_pause_selector);
            }

        } else if ( v == btNext ) {
            // Handle clicks for btNext
        } else if ( v == btnVideoSiwchScreen ) {
            // Handle clicks for btnVideoSiwchScreen
        }
    }







    private class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {

            videoView.start();

            int totalTime=videoView.getDuration();

            tvTotalTime.setText(utils.stringForTime(totalTime));

            seekbarVideo.setMax(totalTime);

            handler.sendEmptyMessage(VIDEO_PROGRESS);
        }
    }

    private class MyonErrorListener implements MediaPlayer.OnErrorListener {
        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            Toast.makeText(SystemVideoPlayerActivity.this, "播放错误....", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private class MyOnComletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(SystemVideoPlayerActivity.this, "视频播放完毕....", Toast.LENGTH_SHORT).show();
        }
    }


    private class VideoSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (b){
                videoView.seekTo(i);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}



