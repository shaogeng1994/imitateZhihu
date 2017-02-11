package com.shao.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.shao.zhihu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17-2-9.
 */

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,null);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我是第" + i + "个");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        ListRecyclerAdapter adapter = new ListRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);


        tabLayout = (TabLayout) root.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("第一"));
        tabLayout.addTab(tabLayout.newTab().setText("第二"));
        tabLayout.addTab(tabLayout.newTab().setText("第三"));
        return root;
    }


}
