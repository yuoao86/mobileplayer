package com.atguigu.mobileplayer.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.atguigu.mobileplayer.R;

public class SystemVideoPlayerActivity extends Activity {

    private VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnPreparedListener(new MyOnPreparedListener());
        videoView.setOnErrorListener(new MyonErrorListener());
        videoView.setOnCompletionListener(new MyOnComletionListener());
        videoView.setMediaController(new MediaController(this));
        Uri data = getIntent().getData();
        if (data != null) {
            videoView.setVideoURI(data);
        }

    }

    private class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            Toast.makeText(SystemVideoPlayerActivity.this, "准备播放视频....", Toast.LENGTH_SHORT).show();
            videoView.start();
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
}



