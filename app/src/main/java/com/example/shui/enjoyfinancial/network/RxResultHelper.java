package com.example.shui.enjoyfinancial.network;

import android.text.TextUtils;

import com.example.shui.enjoyfinancial.network.exception.ServerException;
import com.example.shui.enjoyfinancial.network.exception.TokenException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Mc丶水水 on 2017/3/20 0020.
 */

public class RxResultHelper {
    public static <T> ObservableTransformer<ResultModel<T>, T> handleResult() {
        return new ObservableTransformer<ResultModel<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResultModel<T>> upstream) {
                return upstream.flatMap(new Function<ResultModel<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull ResultModel<T> tResultModel) throws Exception {
                        if (tResultModel == null) {
                            return Observable.error(new ServerException("服务器返回错误", "-1"));
                        }

                        if (TextUtils.equals(tResultModel.getCode(), ResultModel.RESP_SUCCESS)) {
                            //网络请求成功
                            return createData(tResultModel.getData());
                        } else {
                            if (TextUtils.equals(ResultModel.RESP_TOKEN_ERROR, tResultModel.getCode())) {
                                //登录状态失效
                                return Observable.error(new TokenException(tResultModel.getMsg(), tResultModel.getCode()));
                            } else {
                                return Observable.error(new ServerException(tResultModel.getMsg(), tResultModel.getCode()));
                            }
                        }
                    }
                });
            }
        };
    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                e.onNext(data);
                e.onComplete();
            }
        });
    }
}
