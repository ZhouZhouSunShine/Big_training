package coma.example.retrofit2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import coma.example.retrofit2.api.DownFileApi;
import coma.example.retrofit2.api.GetBaiDuApi;
import coma.example.retrofit2.api.RxJavaApi;
import coma.example.retrofit2.api.SimpleGetApi;
import coma.example.retrofit2.api.SimplePostApi;
import coma.example.retrofit2.bean.NewsBean;
import coma.example.retrofit2.bean.NewsBean2;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示当前页面布局
        setContentView(R.layout.activity_main);

        //getBaiDu();
        //simpleGet();
        //simplePost();
        //downFile();
        rxJavaAndRetrofit();
    }

    private void rxJavaAndRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.jisuapi.com/news/")
                .build();

        HashMap<String, String> map = new HashMap<>();
        map.put("channel", "头条");
        map.put("appkey", "cf2efa79d8df0ede");

        Observable<NewsBean2> observable = retrofit.create(RxJavaApi.class).getData(map);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean2>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsBean2 newsBean2) {
                        Log.e(TAG, newsBean2.getResult().getList().get(0).getTitle());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void downFile() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://surl.qq.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        HashMap<String, String> map = new HashMap<>();
        map.put("qbsrc", "51");
        map.put("asr", "4286");

        Call<ResponseBody> call = retrofit.create(DownFileApi.class).downFile(map);

        HttpUrl url = call.request().url();
        Log.e(TAG, url + "");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "isSuccessful");
                    try {
                        ResponseBody body = response.body();
                        InputStream is = body.byteStream();
                        FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "腾讯新闻.apk");
                        byte[] bytes = new byte[2048];
                        int len = 0;
                        while ((len = is.read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                        }
                        is.close();
                        fos.flush();
                        fos.close();
                        Log.e(TAG, "下载完成");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure" + t);
            }
        });
    }

    private void simplePost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.jisuapi.com/news/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HashMap<String, String> map = new HashMap<>();
        map.put("channel", "头条");
        map.put("appkey", "cf2efa79d8df0ede");
        Call<NewsBean2> call = retrofit.create(SimplePostApi.class).getData(map);
        call.enqueue(new Callback<NewsBean2>() {
            @Override
            public void onResponse(Call<NewsBean2> call, Response<NewsBean2> response) {
                NewsBean2 body = response.body();
                Log.e(TAG, body.getResult().getList().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<NewsBean2> call, Throwable t) {

            }
        });
    }

    private void simpleGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/history/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<NewsBean> call = retrofit.create(SimpleGetApi.class).getData(1, 1);
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                NewsBean body = response.body();
                Log.e(TAG, body.getResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {

            }
        });
    }

    private void getBaiDu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(GetBaiDuApi.class).getBaidu();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();

                Log.e(TAG, body);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure" + t);
            }
        });
    }
}
