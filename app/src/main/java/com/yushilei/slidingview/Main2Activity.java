package com.yushilei.slidingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("SlidingView", "Activity dispatchTouchEvent ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("SlidingView", "Activity onTouchEvent ");
        return super.onTouchEvent(event);
    }

    public void show(View view) {
        Toast.makeText(this, "Button show呗点击", Toast.LENGTH_SHORT).show();
    }

    public void del(View view) {
        Toast.makeText(this, "删除呗点击", Toast.LENGTH_SHORT).show();
    }

    public void showParent(View view) {
        Toast.makeText(this, "show父布局呗点击", Toast.LENGTH_SHORT).show();
    }
}
