package com.yushilei.slidingview.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author by  yushilei.
 * @time 2016/9/9 -09:41.
 * @Desc
 */
public class ARecyclerView extends RecyclerView {
    public ARecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    String TAG = "SlidingView";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.d(TAG, "RecyclerView dispatchTouchEvent=" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean b = super.onTouchEvent(e);
        Log.d(TAG, "RecyclerView onTouchEvent=" + b);
        return b;
    }
}
