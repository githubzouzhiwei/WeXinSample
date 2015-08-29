package com.zzw.wexinsample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zzw.wexinsample.R;
import com.zzw.wexinsample.ui.adapter.FragmentContactLvAdapter;
import com.zzw.wexinsample.ui.widget.LetterBar;
import com.zzw.wexinsample.ui.widget.SingleToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录Fragment
 * Created by zouzhiwei on 2015/8/27.
 */
public class ContactFragment extends Fragment implements LetterBar.OnLetterChangeListener {
    //用于标识Log的Tag
    private final String TAG = getClass().getSimpleName();
    //当前Fragment要显示的内容布局
    private View contentView;
    //用于显示联系人的ListView
    private ListView listView;
    //显示点击的字母
    private TextView tvTip;
    //字母索引栏
    private LetterBar letterBar;
    //通讯录ListView的适配器
    private FragmentContactLvAdapter fragmentContactLvAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_contact, null);
            listView = (ListView) contentView.findViewById(R.id.fragment_contact_lv);
            fragmentContactLvAdapter = new FragmentContactLvAdapter();
            listView.setAdapter(fragmentContactLvAdapter);
            letterBar = (LetterBar) contentView.findViewById(R.id.fragment_contact_lb);
            tvTip = (TextView) contentView.findViewById(R.id.fragment_contact_tvTip);
            letterBar.setTvTip(tvTip);
            letterBar.setOnLetterChangeListener(this);
        }
        return contentView;
    }

    @Override
    public void onDestroyView() {
        //防止重复加载同一个布局,否则会闪退hasParent
        ((ViewGroup) contentView.getParent()).removeView(contentView);
        super.onDestroyView();
    }

    /**
     * 获得字母索引栏
     */
    public LetterBar getLetterBar() {
        return letterBar;
    }

    @Override
    public void onLetterChange(String s) {
        //获得点击的字母所在的position
        int position = fragmentContactLvAdapter.getLetterPosition(s);
        listView.setSelection(position);
    }
}
