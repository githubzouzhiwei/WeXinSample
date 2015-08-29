package com.zzw.wexinsample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zzw.wexinsample.R;
import com.zzw.wexinsample.ui.adapter.FragmentWeiXinLVAdapter;

/**
 * 微信Fragment
 * Created by zouzhiwei on 2015/8/27.
 */
public class WeiXinFragment extends Fragment {

    //列表
    private ListView listView;
    //内容布局
    private View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_weixin, null);
            listView = (ListView) contentView.findViewById(R.id.fragmnent_weixin_lv);
            listView.setAdapter(new FragmentWeiXinLVAdapter());
        }
        return contentView;
    }

    @Override
    public void onDestroyView() {
        //防止重复加载同一个布局,否则会闪退hasParent
        ((ViewGroup) contentView.getParent()).removeView(contentView);
        super.onDestroyView();
    }
}
