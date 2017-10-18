package coma.example.leakcanary;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 *
 * 内存泄露后会自动弹出检测工具
 *
 */
public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示当前页面布局
        setContentView(R.layout.activity_main);
        //获取资源ID
        mTextView = (TextView) findViewById(R.id.text);//模拟内存泄露
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("lcj");
            }
        }, 3 * 60 * 1000);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //App.getRefWatcher(this).watch(this);
    }

}
