package com.example.shui.enjoyfinancial.network.bean.resp;

/**
 * Created by Shui on 2017/9/22.
 */

public class AppVersionResp {

    /**
     * down_url : www.baidu.com
     * new_version : 1.0.0
     * low_version : 1.0.0
     */

    private String down_url;
    private String new_version;
    private String low_version;

    public String getDown_url() {
        return down_url;
    }

    public void setDown_url(String down_url) {
        this.down_url = down_url;
    }

    public String getNew_version() {
        return new_version;
    }

    public void setNew_version(String new_version) {
        this.new_version = new_version;
    }

    public String getLow_version() {
        return low_version;
    }

    public void setLow_version(String low_version) {
        this.low_version = low_version;
    }
}
