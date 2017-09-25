package com.example.shui.enjoyfinancial.network.api;

import com.example.shui.enjoyfinancial.network.bean.ResultModel;
import com.example.shui.enjoyfinancial.network.bean.resp.AdResp;
import com.example.shui.enjoyfinancial.network.bean.resp.AppVersionResp;
import com.example.shui.enjoyfinancial.network.bean.resp.RecommendResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 真实接口请求
 * Created by Shui on 16/10/28.
 */

public interface Api {
    //获取广告图片
    @GET("api/v1/Index/ad_list")
    Observable<ResultModel<List<AdResp>>> adList(@Query("type") String type);

    //检查版本更新
    @GET("api/v1/Index/app_ver")
    Observable<ResultModel<AppVersionResp>> appVersion();

    //首页推荐商品
    @GET("api/v1/Commodity/recommend")
    Observable<ResultModel<List<RecommendResp>>> recommend();
}
