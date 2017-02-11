package com.shao.zhihu.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 17-2-6.
 */

public class FloatingButtonBehavior extends FloatingActionButton.Behavior {
    public final static int STATE_SCROLL_UP = 1;
    public final static int STATE_SCROLL_DOWN = 2;

    private OnScrollStateListener onScrollStateListener;

    public FloatingButtonBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

//        if (dyConsumed > 0 && dyUnconsumed == 0) {
//            System.out.println("上滑中。。。");
//        }
//        if (dyConsumed == 0 && dyUnconsumed > 0) {
//            System.out.println("到边界了还在上滑。。。");
//        }
//        if (dyConsumed < 0 && dyUnconsumed == 0) {
//            System.out.println("下滑中。。。");
//        }
//        if (dyConsumed == 0 && dyUnconsumed < 0) {
//            System.out.println("到边界了，还在下滑。。。");
//        }

        if (((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0
                && dyUnconsumed > 0)) && !child.isShown()) {// 显示
            child.show();
            if(onScrollStateListener != null) {
                onScrollStateListener.onScroll(STATE_SCROLL_UP);
            }
        } else if (((dyConsumed < 0 && dyUnconsumed == 0) || (dyConsumed == 0
                && dyUnconsumed < 0)) && child.isShown() ) {
            child.hide();
            if(onScrollStateListener != null) {
                onScrollStateListener.onScroll(STATE_SCROLL_DOWN);
            }
        }
    }

    public static <V extends View> FloatingButtonBehavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (!(behavior instanceof FloatingButtonBehavior)) {
            throw new IllegalArgumentException("The view is not associated with ScaleDownShowBehavior");
        }
        return (FloatingButtonBehavior) behavior;
    }

    public interface OnScrollStateListener {
        void onScroll(int state);
    }


    public void setOnScrollStateListener(OnScrollStateListener onScrollStateListener) {
        this.onScrollStateListener = onScrollStateListener;
    }
}
