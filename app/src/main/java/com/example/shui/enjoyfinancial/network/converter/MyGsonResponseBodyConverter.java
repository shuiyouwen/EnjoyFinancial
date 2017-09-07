package com.example.shui.enjoyfinancial.network.converter;

import android.text.TextUtils;

import com.example.shui.enjoyfinancial.network.ResultModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 解决服务器返回数据不规范
 * Created by Mc丶水水 on 2017/3/30 0030.
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        // 解析code
        JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive("code");
        String code = null;
        if (jsonPrimitive != null) {
            code = jsonPrimitive.getAsString();
        }
        T t = null;
        try {
            // 通过反射获取泛型的实例对象
            Class<T> clazz = (Class<T>) type;
            t = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!TextUtils.equals(code, ResultModel.RESP_SUCCESS)) {
            //返回结果失败
            return (T) gson.fromJson(json, ResultModel.class);
        } else {
            //接口请求成功
            return gson.fromJson(json, type);
        }
    }
}
