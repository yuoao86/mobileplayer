package com.atguigu.mobileplayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.utils.LogUtil;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TitleBar extends LinearLayout implements View.OnClickListener {


    private View tv_search;

    private View rl_game;

    private View iv_record;

    private Context context;


    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tv_search=getChildAt(1);
        rl_game=getChildAt(2);
        iv_record=getChildAt(3);

        tv_search.setOnClickListener(this);
        rl_game.setOnClickListener(this);
        iv_record.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_search:
                LogUtil.d("搜索");
                break;
            case R.id.rl_game:
                LogUtil.d("游戏");
                break;
            case R.id.iv_record:
                LogUtil.d("记录");
                break;
        }
    }
}
