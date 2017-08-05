package com.atguigu.mobileplayer.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.mobileplayer.base.BasePager;
import com.atguigu.mobileplayer.utils.LogUtil;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class NetVideoPager extends BasePager {

    private TextView textView;

    public NetVideoPager(Context context) {

        super(context);
    }

    @Override
    public View initView() {

        textView = new TextView(context);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);

        return textView;
    }


    @Override
    public void initData() {
        super.initData();
        LogUtil.d("网络视频数据被初始化了......");
        //联网
        //网络视频内容
        textView.setText("网络视频内容");

    }
}
