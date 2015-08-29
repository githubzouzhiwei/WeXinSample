package com.zzw.wexinsample.db.entity;

/**
 * 联系人
 * Created by zouzhiwei on 2015/8/28.
 */
public class LinkMan {

    //昵称
    private String nickname;
    //备注名
    private String remarkName;
    //头像URL
    private String headUrl;
    //性别
    private String sex;
    //拼音
    private String pinYin;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }
}
