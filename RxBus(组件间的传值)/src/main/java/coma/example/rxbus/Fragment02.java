package coma.example.rxbus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import coma.example.rxbus.utils.RxBus;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 范晋炜 on 2017/10/18 0018.
 * coma.example.rxbus
 * Fragment02
 */


public class Fragment02  extends Fragment{

    private static final String TAG = "TAG";
    private View view;
    CompositeSubscription c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment02,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView text_View = (TextView) view.findViewById(R.id.text_View);

        RxBus.getInstance().toObservable(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG,"onNext:=========" + s);
                        text_View.setText(s);
                    }
                });
    }

    public void addSubscription(Subscription subscription){
        if(c == null){
            c = new CompositeSubscription();
        }
        c.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(c != null){
            c.unsubscribe();
        }
    }
}
