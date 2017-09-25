package com.example.shui.enjoyfinancial.network.bean.resp;

import android.text.TextUtils;

/**
 * 推荐商品返回结果
 * Created by Shui on 2017/9/25.
 */

public class ProductResp {


    /**
     * id : CG170920000018
     * title : 【cjr】11323123 | 12312
     * is_stages : N
     * price_min : 89999.00
     * preferent_min : 4545.00
     * monthly_min : 0.00
     * img_url : http://106.15.44.140/static/goods_img/20170920/8a6a97933339f08a7dca47e0b64c28cc.png
     */

    private String id;
    private String title;
    private String is_stages;
    private String price_min;
    private String preferent_min;
    private String monthly_min;
    private String img_url;

    /**
     * 是否支持分期
     *
     * @return
     */
    public boolean isSupportStages() {
        return TextUtils.equals(is_stages, "Y");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIs_stages() {
        return is_stages;
    }

    public void setIs_stages(String is_stages) {
        this.is_stages = is_stages;
    }

    public String getPrice_min() {
        return price_min;
    }

    public void setPrice_min(String price_min) {
        this.price_min = price_min;
    }

    public String getPreferent_min() {
        return preferent_min;
    }

    public void setPreferent_min(String preferent_min) {
        this.preferent_min = preferent_min;
    }

    public String getMonthly_min() {
        return monthly_min;
    }

    public void setMonthly_min(String monthly_min) {
        this.monthly_min = monthly_min;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
