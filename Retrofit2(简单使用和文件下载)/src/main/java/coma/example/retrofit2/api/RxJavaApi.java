package coma.example.retrofit2.api;

import java.util.HashMap;

import coma.example.retrofit2.bean.NewsBean2;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 范晋炜 on 2017/10/20 0020.
 * coma.example.retrofit2.api
 * RxJavaApi
 */


public interface RxJavaApi {
    @FormUrlEncoded
    @POST("get/")
    Observable<NewsBean2> getData(@FieldMap HashMap<String, String> map);
}
