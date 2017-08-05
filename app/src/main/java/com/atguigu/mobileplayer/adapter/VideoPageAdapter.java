package com.atguigu.mobileplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.domain.MediaItem;
import com.atguigu.mobileplayer.utils.Utils;

import java.util.ArrayList;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class VideoPageAdapter extends BaseAdapter {

    private ArrayList<MediaItem> mediaItems;
    private Context context;
    private Utils utils;

    public VideoPageAdapter(Context context,ArrayList mediaItems ){
        this.mediaItems=mediaItems;
        this.context=context;
        utils=new Utils();
    }

    @Override
    public int getCount() {
        return mediaItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_video_pager, null);
            holder=new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);
            holder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        MediaItem mediaItem=mediaItems.get(i);
        holder.tv_name.setText(mediaItem.getName());
        holder.tv_size.setText(android.text.format.Formatter.formatFileSize(context,mediaItem.getSize()));
        holder.tv_duration.setText(utils.stringForTime((int) mediaItem.getDuration()));

        return convertView;
    }

    class ViewHolder{
        TextView tv_name;
        TextView tv_duration;
        TextView tv_size;
    }
}
