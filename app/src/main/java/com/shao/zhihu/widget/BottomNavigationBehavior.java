package com.shao.zhihu.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.Timer;

/**
 * Created by root on 17-2-10.
 */

public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<View> {
    private boolean isAnim = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {

            }else if (msg.what == 2) {

            }
        }
    };

    private Timer timer;

    public BottomNavigationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, final View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0) {
            if(isAnim || !child.isShown())return;
            TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f);
            mHiddenAction.setDuration(300);
            mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isAnim = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnim = false;
                    child.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    isAnim = true;
                }
            });
            child.startAnimation(mHiddenAction);
        } else if(dyConsumed < 0) {


            if(isAnim || child.isShown())return;
            TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mShowAction.setDuration(300);
            mShowAction.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isAnim = true;
                    child.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnim = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    isAnim = true;
                }
            });
            child.startAnimation(mShowAction);

        }
    }
}
