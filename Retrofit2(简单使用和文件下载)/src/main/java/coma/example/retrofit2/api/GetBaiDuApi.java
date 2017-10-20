package coma.example.retrofit2.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 范晋炜 on 2017/10/20 0020.
 * coma.example.retrofit2.api
 * GetBaiDuApi
 */


public interface GetBaiDuApi {
    @GET("/")
    Call<String> getBaidu();
}
