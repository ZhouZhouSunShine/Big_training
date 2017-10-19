package coma.example.mycustomview_onmeasureview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button main_myView;
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (MyView) findViewById(R.id.main_myView);

    }

    public void change(View view) {
        myView.changeSize(30,30);
    }
}
