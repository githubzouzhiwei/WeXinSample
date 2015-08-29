package com.zzw.wexinsample.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.zzw.wexinsample.R;
import com.zzw.wexinsample.ui.adapter.MainViewPagerAdapter;
import com.zzw.wexinsample.ui.fragment.ContactFragment;
import com.zzw.wexinsample.ui.fragment.FindFragment;
import com.zzw.wexinsample.ui.fragment.MeFragment;
import com.zzw.wexinsample.ui.fragment.WeiXinFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 * Created by zouzhiwei on 2015/8/27.
 */
public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    //用于标识Log的Tag
    private final String TAG = getClass().getSimpleName();
    //用于显示四个Fragment
    private ViewPager viewPager;
    //四个导航栏按钮
    private RadioButton[] radioButtons;
    //展开更多选项的按钮
    private Button btnMore;
    //搜索的按钮
    private Button btnSearch;
    //ViewPager的适配器
    private MainViewPagerAdapter mainViewPagerAdapter;
    //通讯录Fragment
    private ContactFragment contactFragment;
    //发现Fragment
    private FindFragment findFragment;
    //我Fragment
    private MeFragment meFragment;
    //微信Fragment
    private WeiXinFragment weiXinFragment;
    //装载四个Fragment
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化布局
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        //初始化按钮
        radioButtons = new RadioButton[4];
        radioButtons[0] = (RadioButton) findViewById(R.id.activity_main_rbWeiXin);
        radioButtons[1] = (RadioButton) findViewById(R.id.activity_main_rbContact);
        radioButtons[2] = (RadioButton) findViewById(R.id.activity_main_rbFind);
        radioButtons[3] = (RadioButton) findViewById(R.id.activity_main_rbMe);
        //给四个导航按钮设置监听器
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setOnClickListener(this);
        }
        //初始化Fragment
        weiXinFragment = new WeiXinFragment();
        contactFragment = new ContactFragment();
        findFragment = new FindFragment();
        meFragment = new MeFragment();
        //添加到List
        fragments = new ArrayList<>();
        fragments.add(weiXinFragment);
        fragments.add(contactFragment);
        fragments.add(findFragment);
        fragments.add(meFragment);
        //初始化ViewPager
        viewPager = (ViewPager) findViewById(R.id.activity_main_vp);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        radioButtons[i].setChecked(true);
        if (i == 1) {
            //显示子母索引栏
            contactFragment.getLetterBar().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        //重置字母索引栏
        contactFragment.getLetterBar().reSet();
        if (i == 1) {//滚动
            //隐藏字母索引栏
            contactFragment.getLetterBar().setVisibility(View.GONE);
        } else if (i == 0) {//停止
            //显示子母索引栏
            contactFragment.getLetterBar().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_rbWeiXin:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.activity_main_rbContact:
                viewPager.setCurrentItem(1, false);
                //重置字母索引栏
                contactFragment.getLetterBar().reSet();
                break;
            case R.id.activity_main_rbFind:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.activity_main_rbMe:
                viewPager.setCurrentItem(3, false);
                break;
        }
    }
}
