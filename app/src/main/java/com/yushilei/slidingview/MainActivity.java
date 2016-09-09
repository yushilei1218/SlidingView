package com.yushilei.slidingview;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new RecyAdapter(this));

        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText("SlidView继承CoordinatorLayout,内部2个子View moveView 和DragView（可以是容器），Behavior监听moveView，当moveView x发生变化时，和DragView保持联动。重写SlidView dispatchTouchEv方法，检测x 轴滑动的情况，决定是否开始拉出 或者关闭DragView,并且适当的 requestDisallowInterceptTouchEvent，在Action_Up时，如果已经展开部分DragView，则加载动画,按露出的比例 ，属性动画完成打开和关闭");
    }

    public void jump(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    String TAG = "SlidingView";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.d(TAG, "Activity dispatchTouchEvent=" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Log.d(TAG, "Activity onTouchEvent=" + b);
        return b;
    }
}
