package com.yushilei.slidingview.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yushilei.slidingview.Bean;
import com.yushilei.slidingview.R;

/**
 * @author by  yushilei.
 * @time 2016/9/8 -16:37.
 * @Desc
 */
public class SlidingView extends CoordinatorLayout {
    public SlidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    String TAG = "SlidingView";
    /**
     * 隐藏在右侧的View
     */
    private View dragV;
    /**
     * 可以被滑动的View
     */
    private View moveV;

    /**
     * Action Down 和Move 记录
     */
    float lastX;
    float lastY;
    /**
     * 拦截指数，当滑动距离超过5时，则 拦截事件，不再让子View接收事件
     */
    final float DISTANCE = 5;
    /**
     * 是否处于被拖拽状态
     */
    boolean isDraged = false;
    /**
     * 是否处于open状态
     */
    boolean isOpen = false;

    Bean mBean;

    public void setBean(Bean bean) {
        mBean = bean;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
        if (isOpen) {
            int width = dragV.getWidth();
            moveV.setX(-width);
        } else {
            moveV.setX(0f);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录下来Down时对应的位置
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //如果当前不再拖拽状态，并且Move到Down的距离大于 拦截距离 ，则进入拖拽状态
                if (!isDraged && Math.abs(x - lastX) > DISTANCE) {
                    isDraged = true;
                }
                if (isDraged) {
                    //如果进入拖拽状态，则要求父容器不可拦截子View事件
                    getParent().requestDisallowInterceptTouchEvent(true);
                    int dragVWidth = dragV.getWidth();//dragV的宽度
                    float moveVX = moveV.getX();//滑动View当前x值
                    float movedDistance = x - lastX;//即将滑动的距离
                    //移动
                    if (moveVX + movedDistance >= 0) {
                        //保证Move最大右滑位置
                        moveV.setX(0f);
                    } else {
                        //保证Move最大左滑位置
                        if (moveVX + movedDistance >= -dragVWidth) {
                            moveV.setX(moveVX + movedDistance);
                        } else {
                            moveV.setX(-dragVWidth);
                        }
                    }
                    //重置
                    lastX = x;
                    lastY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
                //如果当前已经处于拖拽状态
                if (isDraged) {
                    float moveVX = moveV.getX();
                    int dragVWidth = dragV.getWidth();
                    //如果dragV已经露出大于1/2
                    if (Math.abs(moveVX) > dragVWidth / 2) {
                        //全部拉出
                        ObjectAnimator animator = ObjectAnimator.ofFloat(moveV, "X", moveV.getX(), -dragVWidth);
                        animator.setDuration(200);
                        animator.start();
                        isOpen = true;
                        if (mBean != null) {
                            mBean.setIsOpen(isOpen);
                        }
                    } else {
                        //如果dragV已经露出小于1/2
                        //全部收回
                        ObjectAnimator animator = ObjectAnimator.ofFloat(moveV, "X", moveV.getX(), 0f);
                        animator.setDuration(200);
                        animator.start();
                        isOpen = false;
                        if (mBean != null) {
                            mBean.setIsOpen(isOpen);
                        }
                    }
                    //重置状态
                    isDraged = false;
                    //return true则子View无法接收到 Up事件
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //如果处于拖拽状态 则当前View状态返回true
        if (isDraged) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //可滑动View
        moveV = findViewById(R.id.id_slid_move_view);
        //被拖拽出来的View
        dragV = findViewById(R.id.id_slid_drag_view);
    }
}
