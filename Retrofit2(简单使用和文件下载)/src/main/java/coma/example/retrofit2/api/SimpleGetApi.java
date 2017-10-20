package coma.example.retrofit2.api;

import coma.example.retrofit2.bean.NewsBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 范晋炜 on 2017/10/20 0020.
 * coma.example.retrofit2.api
 * SimpleGetApi
 */


public interface SimpleGetApi {
    @GET("content/{number}/{page}")
    Call<NewsBean> getData(@Path("number") int n, @Path("page") int p);
}
