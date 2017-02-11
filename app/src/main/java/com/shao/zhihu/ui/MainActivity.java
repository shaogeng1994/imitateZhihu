package com.shao.zhihu.ui;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.shao.zhihu.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.main_tab);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        mViewPager.setAdapter(new ViewPagerAdapter(list, getSupportFragmentManager()));

        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_bottomtabbar_feed));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_bottomtabbar_discover));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_bottomtabbar_notification));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_bottomtabbar_message));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_bottomtabbar_more));
        mTabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        mTabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#dddddd"), PorterDuff.Mode.MULTIPLY);
        mTabLayout.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#dddddd"), PorterDuff.Mode.MULTIPLY);
        mTabLayout.getTabAt(3).getIcon().setColorFilter(Color.parseColor("#dddddd"), PorterDuff.Mode.MULTIPLY);
        mTabLayout.getTabAt(4).getIcon().setColorFilter(Color.parseColor("#dddddd"), PorterDuff.Mode.MULTIPLY);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#dddddd"), PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            }
        });


        setSupportActionBar(toolbar);

    }


    private class ViewPagerAdapter extends PagerAdapter {
        private List<Fragment> list;
        private FragmentManager fragmentManager;

        private ViewPagerAdapter(List<Fragment> list, FragmentManager fragmentManager) {
            this.list = list;
            this.fragmentManager = fragmentManager;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = list.get(position);
            if(!fragment.isAdded()){
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(fragment, fragment.getClass().getSimpleName());
                ft.commit();

                fragmentManager.executePendingTransactions();
            }

            if(fragment.getView().getParent() == null){
                container.addView(fragment.getView());
            }

            return fragment.getView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position).getView());
        }
    }
}
