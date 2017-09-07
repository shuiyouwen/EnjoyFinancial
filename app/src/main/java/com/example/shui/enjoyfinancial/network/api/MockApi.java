package com.example.shui.enjoyfinancial.network.api;//package com.iuicity.xinjr.network.api;
//
//import com.google.gson.Gson;
//import com.iuicity.xinjr.network.bean.ResultModel;
//import com.iuicity.xinjr.network.bean.resp.BannersResp;
//import com.iuicity.xinjr.network.bean.resp.FaddishResp;
//import com.iuicity.xinjr.network.bean.resp.VersionResp;
//
//import java.util.List;
//
//import io.reactivex.Observable;
//import retrofit2.http.Field;
//import retrofit2.mock.BehaviorDelegate;
//
///**
// * 模拟接口
// * Created by Mc丶水水 on 2017/3/31 0031.
// */
//
//public class MockApi implements Api {
//    private final BehaviorDelegate<Api> mDelegate;
//    private Gson mGson = new Gson();
//
//    public MockApi(BehaviorDelegate<Api> delegate) {
//        mDelegate = delegate;
//    }
//
//    @Override
//    public Observable<ResultModel<String>> getPubkey(@Field("equipment") String equipment) {
//        ResultModel<String> target = new ResultModel<>();
//        target.setCode("000");
//        target.setData("asdasdhaksdhkah");
//        target.setMsg("获取公钥出错");
//        return mDelegate.returningResponse(target).getPubkey("");
//    }
//
//    @Override
//    public Observable<ResultModel<Object>> login(String phone, String password) {
//        return null;
//    }
//
//
////    @Override
////    public Observable<ResultModel<LoginResp>> login(@Field("equipment") String equipment, @Field("encrypt") String encrypt, @Field("version") String version, @Field("platform") String platform, @Field("from") String from) {
////        ResultModel<LoginResp> target = new ResultModel<>();
////        target.setCode("000");
////        LoginResp loginResp = new LoginResp();
////        loginResp.setStatus("state");
////        loginResp.setToken("token");
////        loginResp.setType("type");
////        loginResp.setUid("uid");
////        target.setData(loginResp);
////        target.setMsg("登录出错");
////        return mDelegate.returningResponse(target).login("", "", "", "", "");
////    }
//
//    @Override
//    public Observable<ResultModel<VersionResp>> getVersion(String phoneType) {
//        return null;
//    }
//
//    @Override
//    public Observable<ResultModel<List<BannersResp>>> getBanners(String place) {
//        return null;
//    }
//
//    @Override
//    public Observable<ResultModel<List<FaddishResp>>> faddish() {
//        return null;
//    }
//
//}
