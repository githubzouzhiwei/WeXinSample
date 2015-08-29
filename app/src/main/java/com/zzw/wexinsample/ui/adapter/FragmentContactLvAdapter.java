package com.zzw.wexinsample.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzw.wexinsample.R;
import com.zzw.wexinsample.db.entity.LinkMan;
import com.zzw.wexinsample.util.pinyin.PinYin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通讯录ListView的适配器
 * Created by zouzhiwei on 2015/8/27.
 */
public class FragmentContactLvAdapter extends BaseAdapter {

    //用于标识Log的Tag
    private final String TAG = getClass().getSimpleName();
    //联系人姓名
    private String[] linkMainName = new String[]{"吖包", "吖迪", "吖丰", "吖建", "吖里", "吖力歌", "吖明", "吖易", "卜培文", "蔡瑞鹏", "程俊",
            "陈浩斌", "陈静琅", "陈俊辉", "陈全和", "陈汝杨", "陈伟荣", "陈晓锋", "陈仪婷", "待续..", "邓光华", "方晓彬",
            "冯金凤", "葛武荣", "管焱威", "顾惠娜", "贵娘", "何海航老师", "何杰勇", "何耀轩", "HL", "黄浩燕", "黄家玲",
            "黄丽", "黄启东", "黄舒婷", "黄炎森", "黄一锋", "黄银风", "黄照恺", "胡霞", "Jason Lee。", "嵇建丽", "赖茂文",
            "雷灿权", "李安乾", "李欢欢", "李嘉靖", "黎俊豪", "林春辉", "林映峰", "林玉生", "林喆", "林紫琼", "李啟满",
            "黎清奇", "刘钊辉", "李文箐", "李休贤", "龙威", "罗辉", "骆培生", "罗晓峰", "倪壮杰", "丘家熙", "宋竹良",
            "孙素芬", "微信团队", "文件传输助手", "温伟辉", "吴家健", "肖明辉", "小微", "谢尚文", "杨颖", "叶丰华",
            "叶俊", "殷志禧", "游晓峰", "袁青", "曾舒媛", "曾松泽", "张天顺", "张伟禺", "张文杰", "詹学佳", "郑晓雯",
            "郑振协", "钟坤锡", "钟威娜", "钟意娜", "朱楚奇", "zouzhiwei"};
    //联系人数据
    private List<LinkMan> linkMans;
    //索引字母
    private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //用于标识每个View的位置，防止图片乱序
    private SparseArray<View> sparseArray;
    //每个Item要显示的数据
    private List data;

    public FragmentContactLvAdapter() {
        sparseArray = new SparseArray<>();
        initLinkMan();
        sortLinkMan();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        data = new ArrayList();
        //标识是否插入字母
        boolean hadInsertLetter = false;
        for (int i = 0; i < letters.length; i++) {
            hadInsertLetter = false;
            for (int j = 0; j < linkMans.size(); j++) {
                if (letters[i].equals(String.valueOf(linkMans.get(j).getPinYin().charAt(0)))) {
                    if (!hadInsertLetter) {
                        hadInsertLetter = true;
                        data.add(letters[i]);
                    }
                    data.add(linkMans.get(j));
                }
            }
        }
    }

    /**
     * 初始化联系人
     */
    private void initLinkMan() {
        linkMans = new ArrayList<>();
        for (int i = 0; i < linkMainName.length; i++) {
            LinkMan linkMan = new LinkMan();
            linkMan.setRemarkName(linkMainName[i]);
            linkMan.setPinYin(PinYin.getPinYin(linkMainName[i]));
            linkMans.add(linkMan);
        }
    }

    /**
     * 排序联系人
     */
    private void sortLinkMan() {
        for (int i = 0; i < linkMans.size(); i++) {
            for (int j = i + 1; j < linkMans.size(); j++) {
                if (linkMans.get(i).getPinYin().compareTo(linkMans.get(j).getPinYin()) > 0) {
                    LinkMan temp = linkMans.get(i);
                    linkMans.set(i, linkMans.get(j));
                    linkMans.set(j, temp);
                }
            }
        }
    }

    @Override
    public int getCount() {
        //如果联系人数大于0,则返回数据大小+4,否则等于4
        return data.size() + 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 获得字母的position
     */
    public int getLetterPosition(String s) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) instanceof String) {
                if (((String) data.get(i)).equals(s)) {
                    return (i + 4);
                }
            }
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //顶部四个非联系人选项
        if (position == 0 || position == 1 || position == 2 || position == 3) {
            ViewHolder viewHolder = null;
            if ((convertView = sparseArray.get(position)) == null) {
                convertView = View.inflate(parent.getContext(), R.layout.fragment_contact_lv_item, null);
                viewHolder = new ViewHolder();
                viewHolder.ivPic = (ImageView) convertView.findViewById(R.id.fragment_contact_lv_item_ivHead);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.fragment_contact_lv_item_tvName);
                convertView.setTag(viewHolder);
                sparseArray.put(position, convertView);
            }
            viewHolder = (ViewHolder) convertView.getTag();
            if (position == 0) {
                viewHolder.ivPic.setImageResource(R.mipmap.default_fmessage);
                viewHolder.tvName.setText("新的朋友");
            } else if (position == 1) {
                viewHolder.ivPic.setImageResource(R.mipmap.default_chatroom);
                viewHolder.tvName.setText("群聊");
            } else if (position == 2) {
                viewHolder.ivPic.setImageResource(R.mipmap.default_contactlabel);
                viewHolder.tvName.setText("标签");
            } else {
                viewHolder.ivPic.setImageResource(R.mipmap.default_servicebrand_contact);
                viewHolder.tvName.setText("公众号");
            }
            return convertView;
        } else {
            //判断是否为字母
            if (data.get(position - 4) instanceof String) {
                TextView tvLetter;
                if ((tvLetter = (TextView) sparseArray.get(position)) == null) {
                    tvLetter = new TextView(parent.getContext());
                    tvLetter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    tvLetter.setBackgroundColor(Color.LTGRAY);
                    tvLetter.setTextColor(Color.BLACK);
                    tvLetter.setPadding(32, 8, 8, 8);
                    tvLetter.setTextSize(14);
                    tvLetter.setText(((String) data.get(position - 4)));
                    sparseArray.put(position, tvLetter);
                }
                return tvLetter;
            } else {
                ViewHolder viewHolder = null;
                if ((convertView = sparseArray.get(position)) == null) {
                    convertView = View.inflate(parent.getContext(), R.layout.fragment_contact_lv_item, null);
                    viewHolder = new ViewHolder();
                    viewHolder.ivPic = (ImageView) convertView.findViewById(R.id.fragment_contact_lv_item_ivHead);
                    viewHolder.tvName = (TextView) convertView.findViewById(R.id.fragment_contact_lv_item_tvName);
                    convertView.setTag(viewHolder);
                    sparseArray.put(position, convertView);
                }
                viewHolder = (ViewHolder) convertView.getTag();
                LinkMan linkMan = (LinkMan) data.get(position - 4);
                viewHolder.tvName.setText(linkMan.getRemarkName());
                return convertView;
            }
        }
    }

    /**
     * 定义内部类，必须为静态，避免内部类持有外部类的引用，内存无法清除
     */
    private static class ViewHolder {
        //图片
        public ImageView ivPic;
        //名称
        public TextView tvName;
    }
}
