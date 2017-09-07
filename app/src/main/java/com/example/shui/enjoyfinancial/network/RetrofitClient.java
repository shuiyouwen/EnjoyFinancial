package com.example.shui.enjoyfinancial.network;

import com.example.shui.enjoyfinancial.BuildConfig;
import com.example.shui.enjoyfinancial.network.api.Api;
import com.example.shui.enjoyfinancial.network.converter.GsonConverterFactory;
import com.example.shui.enjoyfinancial.network.interceptor.HttpLoggingInterceptor;
import com.example.shui.enjoyfinancial.utils.GetConfiguration;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * retrofit实例（包含公共参数）
 * Created by Shui on 16/10/28.
 */

public class RetrofitClient {
    private static Retrofit mRetrofit;
//    private static BasicParamsInterceptor.Builder sParamsBuilder;

    static {
        String baseUrl = GetConfiguration.getInstance().getBaseUrl();
        HttpUrl url = HttpUrl.parse(baseUrl);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
//        sParamsBuilder = new BasicParamsInterceptor.Builder();
        //  sParamsBuilder.addParam("platform", DeviceInfo.PLATFORM);
     //   sParamsBuilder.addParam("version", DeviceInfo.get().getAppVersion());
//        builder.addInterceptor(sParamsBuilder.build());
        mRetrofit = new Retrofit.Builder().baseUrl(url)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    /**
     * 获取真实的网络请求
     *
     * @return
     */
    public static Api getApi() {
        return mRetrofit.create(Api.class);
    }

//    /**
//     * 获取模拟请求数据（上线前，要检查此方法是否被调用，防止部分接口使用的是假数据）
//     *
//     * @return
//     */
//    public static Api getMockApi() {
//        NetworkBehavior behavior = NetworkBehavior.create();
//        MockRetrofit mockRetrofit = new MockRetrofit.Builder(mRetrofit)
//                .networkBehavior(behavior)
//                .build();
//        behavior.setDelay(2, TimeUnit.SECONDS);
//        BehaviorDelegate<Api> delegate = mockRetrofit.create(Api.class);
//        return new MockApi(delegate);
//    }
}
