package com.example.shui.enjoyfinancial.network.resp;

import android.text.TextUtils;

/**
 * Created by Shui on 2017/8/2.
 */

public class BannersResp {

    /**
     * img_url : home-banner.png
     * skip : 1
     * skip_url : www.baidu.com
     */
    private static final String SKIP_FLAG = "1";//1跳转  0标示不跳转

    private String img_url;
    private String skip;
    private String skip_url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getSkip_url() {
        return skip_url;
    }

    public void setSkip_url(String skip_url) {
        this.skip_url = skip_url;
    }

    /**
     * 是否跳转
     *
     * @return
     */
    public boolean isSkip() {
        return TextUtils.equals(skip, SKIP_FLAG);
    }
}
