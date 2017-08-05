package com.atguigu.mobileplayer.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.VideoView;

import com.atguigu.mobileplayer.R;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
class SystemVideoPlayer extends AppCompatActivity{

    private VideoView videoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);

        videoView=(VideoView) findViewById(R.id.videoView);
        Uri data = getIntent().getData();
        if (data!=null){
            videoView.setVideoURI(data);
            videoView.setOnPreparedListener(new MyOnPreparedListener());
            videoView.setOnErrorListener(new MyonErrorListener());
            videoView.setOnCompletionListener(new MyOnComletionListener());


        }


    }

    private class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            Toast.makeText(SystemVideoPlayer.this, "准备播放视频....", Toast.LENGTH_SHORT).show();
            videoView.start();
        }
    }

    private class MyonErrorListener implements MediaPlayer.OnErrorListener {
        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            Toast.makeText(SystemVideoPlayer.this, "播放错误....", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private class MyOnComletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(SystemVideoPlayer.this, "视频播放完毕....", Toast.LENGTH_SHORT).show();
        }
    }
}
