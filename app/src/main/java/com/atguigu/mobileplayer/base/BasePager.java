package com.atguigu.mobileplayer.base;

import android.content.Context;
import android.view.View;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public abstract class BasePager {

    public final Context context;

    public View rootView;
    public boolean isInitData=false;


    public BasePager(Context context){

        this.context=context;

        rootView=initView();

    }

    public abstract View initView();


    public void initData() {

    }


}
