package com.zzw.wexinsample.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zzw.wexinsample.ui.fragment.ContactFragment;
import com.zzw.wexinsample.ui.fragment.FindFragment;
import com.zzw.wexinsample.ui.fragment.MeFragment;
import com.zzw.wexinsample.ui.fragment.WeiXinFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面的ViewPager的适配器
 * Created by zouzhiwei on 2015/8/27.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    //装载了四个Fragment的List
    private List<Fragment> fragments;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
