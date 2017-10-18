package coma.example.mymatrix;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyView myview;
    private GridLayout gride;
    private Button button1;
    private Button button2;
    private float[] floats = new float[9];//矩阵需要的float数组
    private EditText[] texts = new EditText[9];//为了显示效果需要
    private int width;//每一个输入框的width
    private int height;//每一个输入框的height

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示当前页面布局
        setContentView(R.layout.activity_main);
        initView();

        gride.post(new Runnable() {
            @Override
            public void run() {
                width = gride.getWidth() / 3;
                height = gride.getHeight() / 3;
                //添加进格子布局
                addedit();
                initMatricx();
            }
        });
    }

    private void initMatricx() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                texts[i].setText(String.valueOf(1));
            } else {
                texts[i].setText(String.valueOf(0));
            }
        }
    }

    private void addedit() {
        for (int i = 0; i < 9; i++) {
            EditText editText = new EditText(MainActivity.this);
            //设置显示位置
            editText.setGravity(Gravity.CENTER);
            //添加进格子布局
            gride.addView(editText,width,height);
            //给上边的EditText数组赋值
            texts[i] = editText;
        }
    }

    private void initView() {
        myview = (MyView) findViewById(R.id.myview);
        gride = (GridLayout) findViewById(R.id.gride);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                getImageMatricx();
                Matrix matrix = new Matrix();
                matrix.setValues(floats);
                myview.setMatrix(matrix);
                myview.invalidate();
                break;
            case R.id.button2:
                initMatricx();
                getImageMatricx();
                //实例化矩阵
                Matrix matrix1 = new Matrix();
                matrix1.setValues(floats);
                myview.setMatrix(matrix1);
                myview.invalidate();
                break;
        }
    }

    private void getImageMatricx() {
        //因为矩阵需要一个一个float数组所以我们给他一个float数组
        for (int i=0;i<9;i++){
            floats[i]=Float.parseFloat(texts[i].getText().toString());
        }
    }

}
