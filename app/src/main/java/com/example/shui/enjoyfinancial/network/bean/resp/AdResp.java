package com.example.shui.enjoyfinancial.network.bean.resp;

import android.text.TextUtils;

/**
 * 广告
 * Created by Shui on 2017/9/21.
 */

public class AdResp {


    /**
     * cnt : 1
     * img_url : ?
     * is_skip : N
     * skip_url : null
     */

    private int cnt;
    private String img_url;
    private String is_skip;
    private Object skip_url;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getIs_skip() {
        return is_skip;
    }

    public void setIs_skip(String is_skip) {
        this.is_skip = is_skip;
    }

    public Object getSkip_url() {
        return skip_url;
    }

    public void setSkip_url(Object skip_url) {
        this.skip_url = skip_url;
    }

    /**
     * 能否跳转
     *
     * @return
     */
    public boolean isSkip() {
        return TextUtils.equals(is_skip.toUpperCase(), "Y");
    }
}
