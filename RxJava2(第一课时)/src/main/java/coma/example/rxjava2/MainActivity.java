package coma.example.rxjava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示当前页面布局
        setContentView(R.layout.activity_main);
        /**
         *
         * 上下游发送事件
         *
         */
        /*Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emmitor) throws Exception {
                Log.e(TAG, "emmitor   1");
                emmitor.onNext(1);
                Log.e(TAG, "emmitor   2");
                emmitor.onNext(2);
                Log.e(TAG, "emmitor   3");
                //上游可以不发送onComplete或onError.
                //当上游发送了一个onError后, 上游onError之后的事件将继续发送,
                // 而下游收到onError事件之后将不再继续接收事件.
                emmitor.onError(new NullPointerException());
                emmitor.onNext(3);
                //当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送,
                // 而下游收到onComplete事件之后将不再继续接收事件.
                Log.e(TAG, "emmitor   onComplete");
                emmitor.onComplete();
                Log.e(TAG, "emmitor   4");
                emmitor.onNext(4);
                Log.e(TAG, "emmitor   5");
                emmitor.onNext(5);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                boolean disposed = d.isDisposed();
                d.dispose();
                Log.e(TAG, disposed+"");
            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, value + "");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError" + e);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };

        observable.subscribe(observer);*/

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emmitor) throws Exception {
                Log.e(TAG, "上游的线程=======" + Thread.currentThread().getName());
                Log.e(TAG, "emmitor   1");
                emmitor.onNext(1);
                Log.e(TAG, "emmitor   2");
                emmitor.onNext(2);
                Log.e(TAG, "emmitor   3");
                /*emmitor.onError(new NullPointerException());
                emmitor.onNext(3);
                Log.e(TAG, "emmitor   onComplete");
                emmitor.onComplete();
                Log.e(TAG, "emmitor   4");
                emmitor.onNext(4);
                Log.e(TAG, "emmitor   5");
                emmitor.onNext(5);*/
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "下游的线程=======" + Thread.currentThread().getName());
                        Log.e(TAG, integer + "");
                    }
                });

        new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
