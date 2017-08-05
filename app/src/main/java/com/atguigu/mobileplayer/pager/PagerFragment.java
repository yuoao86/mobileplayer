package com.atguigu.mobileplayer.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.mobileplayer.base.BasePager;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class PagerFragment extends Fragment {

    private BasePager basePager;

    public PagerFragment(BasePager basePager){
        this.basePager=basePager;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (basePager!=null){
            return basePager.rootView;
        }
        return null;
    }
}
