package com.yushilei.slidingview.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.yushilei.slidingview.R;

/**
 * @author by  yushilei.
 * @time 2016/9/8 -16:38.
 * @Desc
 */
public class SlidBehavior extends CoordinatorLayout.Behavior<View> {
    public SlidBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //dragV 依赖于 moveV
        return dependency.getId() == R.id.id_slid_move_view;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float x = dependency.getX();
        int width = dependency.getWidth();
        //保证dragV的高度与 MoveV一致
        ViewGroup.LayoutParams params = child.getLayoutParams();
        params.height = dependency.getHeight();
        //保证dragV的位置始终在 moveX的右侧
        child.setX(width + x);
        return true;
    }
}
