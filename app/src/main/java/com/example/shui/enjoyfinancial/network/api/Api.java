package com.example.shui.enjoyfinancial.network.api;

import com.example.shui.enjoyfinancial.network.bean.ResultModel;
import com.example.shui.enjoyfinancial.network.bean.resp.AdResp;
import com.example.shui.enjoyfinancial.network.bean.resp.AppVersionResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 真实接口请求
 * Created by Shui on 16/10/28.
 */

public interface Api {
    /**
     * 获取banner
     *
     * @return
     */
    @GET("api/v1/Index/ad_list")
    Observable<ResultModel<List<AdResp>>> adList(@Query("type") String type);

    @GET("api/v1/Index/app_ver")
    Observable<ResultModel<AppVersionResp>> appVersion(@Query("device") String device);

    @GET("api/v1/Index/recommend")
    Observable<ResultModel<Object>> recommend(@Query("platform") String platform,
                                                       @Query("device") String device);
}
