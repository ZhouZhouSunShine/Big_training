package coma.example.retrofit2.api;

import java.util.HashMap;

import coma.example.retrofit2.bean.NewsBean2;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by 范晋炜 on 2017/10/20 0020.
 * coma.example.retrofit2.api
 * SimplePostApi
 */


public interface SimplePostApi {
    @GET("get/")
    Call<NewsBean2> getData(@QueryMap HashMap<String,String> map);
}
