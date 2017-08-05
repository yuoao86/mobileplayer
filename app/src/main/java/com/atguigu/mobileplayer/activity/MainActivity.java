package com.atguigu.mobileplayer.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.base.BasePager;
import com.atguigu.mobileplayer.pager.AudioPager;
import com.atguigu.mobileplayer.pager.NetAudioPager;
import com.atguigu.mobileplayer.pager.NetVideoPager;
import com.atguigu.mobileplayer.pager.PagerFragment;
import com.atguigu.mobileplayer.pager.VideoPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_bottom_tag;
    private List<BasePager> basePagers;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_bottom_tag=(RadioGroup) findViewById(R.id.rg_bottom_tag);

        basePagers = new ArrayList<>();
        basePagers.add(new VideoPager(this));
        basePagers.add(new AudioPager(this));
        basePagers.add(new NetVideoPager(this));
        basePagers.add(new NetAudioPager(this));

        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_bottom_tag.check(R.id.rb_video);//默认选中首页
    }



    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
            switch (checkedId){
                default:
                    position=0;
                    break;
                case R.id.rb_audio:
                    position=1;
                    break;
                case R.id.rb_netvideo:
                    position=2;
                    break;
                case R.id.rb_netaudion:
                    position=3;
                    break;
            }
            setFragment();
        }

    }

    private void setFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fl_main_content,new PagerFragment(getBasePager()));
        ft.commit();
    }

    private BasePager getBasePager() {
        BasePager basePager=basePagers.get(position);
        if (basePager!=null&&!basePager.isInitData){

            basePager.initData();
            basePager.isInitData=true;
        }
        return basePager;
    }
}
