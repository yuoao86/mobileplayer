package com.atguigu.mobileplayer.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.activity.SystemVideoPlayerActivity;
import com.atguigu.mobileplayer.adapter.VideoPageAdapter;
import com.atguigu.mobileplayer.base.BasePager;
import com.atguigu.mobileplayer.domain.MediaItem;
import com.atguigu.mobileplayer.utils.LogUtil;

import java.util.ArrayList;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class VideoPager extends BasePager {

    private TextView tv_nomedia;
    private ListView listView;
    private ProgressBar pb_loading;

    private ArrayList<MediaItem> mediaItems;
    private Handler handler;



    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.video_pager, null);

        tv_nomedia=(TextView) view.findViewById(R.id.tv_nomedia);
        listView=(ListView) view.findViewById(R.id.listView);
        pb_loading=(ProgressBar) view.findViewById(R.id.pb_loading);

        listView.setOnItemClickListener(new MyOnItemClickListener());

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (mediaItems!=null&&mediaItems.size()>0){
                    listView.setAdapter(new VideoPageAdapter(context,mediaItems));
                    pb_loading.setVisibility(View.GONE);
                }else {
                    tv_nomedia.setVisibility(View.VISIBLE);
                }

            }
        };

        return view;
    }


    @Override
    public void initData() {
        super.initData();
        LogUtil.d("本地视频数据被初始化了......");

        getDataFromLocal();



    }

    private void getDataFromLocal() {
        mediaItems=new ArrayList<>();
        new Thread(){
            @Override
            public void run() {
                super.run();
                ContentResolver resolver = context.getContentResolver();
                Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs={
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.ARTIST
                };

                Cursor cursor = resolver.query(uri, objs, null, null, null);
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        MediaItem mediaItem = new MediaItem();
                        mediaItems.add(mediaItem);
                        mediaItem.setName(cursor.getString(0));
                        mediaItem.setDuration(cursor.getLong(1));
                        mediaItem.setData(cursor.getString(2));
                        mediaItem.setSize(cursor.getLong(3));
                        mediaItem.setArtist(cursor.getString(4));
                    }

                    Message message = new Message();
                    handler.sendMessage(message);

                }
                cursor.close();
            }
        }.start();
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(context, SystemVideoPlayerActivity.class);
            Uri uri=Uri.parse(mediaItems.get(i).getData());
            intent.setDataAndType(uri,"video/*");
            context.startActivity(intent);
        }
    }



}
