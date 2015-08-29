package com.zzw.wexinsample.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzw.wexinsample.R;

/**
 * 微信Fragment的ListView的适配器
 * Created by zouzhiwei on 2015/8/27.
 */
public class FragmentWeiXinLVAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.fragment_weixin_lv_item, null);
            viewHolder = new ViewHolder();
            viewHolder.ivPic = (ImageView) convertView.findViewById(R.id.fragment_weixin_lv_item_ivPic);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.fragment_weixin_lv_item_tvTitle);
            viewHolder.tvBrief = (TextView) convertView.findViewById(R.id.fragment_weixin_lv_item_tvBrief);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.fragment_weixin_lv_item_tvTime);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        if (position == 1) {
            viewHolder.ivPic.setImageResource(R.mipmap.default_readerapp);
            viewHolder.tvTitle.setText("腾讯新闻");
            viewHolder.tvBrief.setText("揭秘阅兵官兵伙食 每日吃6餐");
        } else if (position == 2) {
            viewHolder.ivPic.setImageResource(R.mipmap.default_qqmail);
            viewHolder.tvTitle.setText("QQ邮箱提醒");
            viewHolder.tvBrief.setText("新浪微博开放平台:微博开放平台接口入tips");
        } else if (position == 3) {
            viewHolder.ivPic.setImageResource(R.mipmap.default_qqsync);
            viewHolder.tvTitle.setText("通讯录同步助手");
            viewHolder.tvBrief.setText("你已经66天没有备份了");
        }
        return convertView;
    }

    private static class ViewHolder {
        //图片
        public ImageView ivPic;
        //标题
        public TextView tvTitle;
        //简介
        public TextView tvBrief;
        //时间
        public TextView tvTime;
    }
}
