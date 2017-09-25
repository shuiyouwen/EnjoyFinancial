package com.example.shui.enjoyfinancial.network.api;

import com.example.shui.enjoyfinancial.network.bean.ResultModel;
import com.example.shui.enjoyfinancial.network.bean.resp.AdResp;
import com.example.shui.enjoyfinancial.network.bean.resp.AppVersionResp;
import com.example.shui.enjoyfinancial.network.bean.resp.CategoryResp;
import com.example.shui.enjoyfinancial.network.bean.resp.ProductResp;

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
     * 获取广告图片
     *
     * @param type 广告类型
     * @return
     */
    @GET("api/v1/Index/ad_list")
    Observable<ResultModel<List<AdResp>>> adList(@Query("type") String type);

    /**
     * 检查版本更新
     *
     * @return
     */
    @GET("api/v1/Index/app_ver")
    Observable<ResultModel<AppVersionResp>> appVersion();

    /**
     * 首页推荐商品
     *
     * @return
     */
    @GET("api/v1/Commodity/recommend")
    Observable<ResultModel<List<ProductResp>>> recommend();

    /**
     * 分类商品列表
     *
     * @param classOne 一级分类
     * @param classTwo 二级分类
     * @param sort     排序
     * @param page     页数
     * @return
     */
    @GET("api/v1/Commodity/goods_list")
    Observable<ResultModel<List<ProductResp>>> goodsList(@Query("class_one") String classOne,
                                                         @Query("class_two") String classTwo,
                                                         @Query("sort") String sort,
                                                         @Query("page") String page);

    /**
     * 商品分类
     *
     * @return
     */
    @GET("api/v1/Commodity/goods_type")
    Observable<ResultModel<List<CategoryResp>>> goodsType(@Query("pid") String pid);
}
