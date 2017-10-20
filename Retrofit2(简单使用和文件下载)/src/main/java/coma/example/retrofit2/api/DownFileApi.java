package coma.example.retrofit2.api;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 范晋炜 on 2017/10/20 0020.
 * coma.example.retrofit2.api
 * DownFileApi
 */


public interface DownFileApi {

    @FormUrlEncoded
    @POST("195D0D")
    Call<ResponseBody> downFile(@FieldMap HashMap<String, String> map);
}
