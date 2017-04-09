package com.shidoe.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HandsomeXu on 2017/4/8.
 */

public class MainFragment extends Fragment {
    private ViewPager mViewPager;
    //private BottomNavigationView mBottomNavigationView;
    private List<Fragment> mFragmentList;
    private MyViewPagerAdapter mAdapter;
    private BottomNavigationView mBottomNavigationView;
    private String[] titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        setBottomNavigationView();
    }

    private void setBottomNavigationView() {
        mBottomNavigationView = (BottomNavigationView)getView().findViewById(R.id.bottomNavigation);

        int[] images = {R.drawable.ic_menu_camera, R.drawable.ic_menu_gallery,
                R.drawable.ic_menu_manage, R.drawable.ic_menu_send};
        int[] colors = {ContextCompat.getColor(getContext(),R.color.colorAccent),
                ContextCompat.getColor(getContext(),R.color.colorAccent),
                ContextCompat.getColor(getContext(),R.color.colorAccent),
                ContextCompat.getColor(getContext(),R.color.colorAccent)};

        if (mBottomNavigationView != null) {
            /**
             * 汉字是否一直显示:(false:显示选中的；true全部显示)
             */
            mBottomNavigationView.isWithText(true);
            /**
             * 左边竖直方向放置
             */
            // mBottomNavigationView.activateTabletMode();
            /**
             * 整体背景色
             */
            mBottomNavigationView.isColoredBackground(true);
            /**
             * 当设置为false时icon和汉字显示颜色能用
             */
            mBottomNavigationView.isColoredBackground(false);
         //   mBottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.secondColor));
            /**
             * 选中字体的大小
             */
           // mBottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen.text_active));
            /**
             * 未选中字体的大小
             */
          //  mBottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen.text_active));
            /**
             * 设置字体
             */
            mBottomNavigationView.setFont(Typeface.DEFAULT_BOLD);
            /**
             * 去掉影子
             */
            mBottomNavigationView.disableShadow();
            /**
             *关联viewpager
             */
            mBottomNavigationView.setUpWithViewPager(mViewPager , colors , images);
        }
    }

    private void initData() {
        titles = new String[]{"汽车","家居","网络","多媒体"};
        mFragmentList = new ArrayList<>();
        Fragment fragment1,fragment2,fragment3,fragment4;
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);
    }

    private void initView() {
        mViewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        mAdapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager(),mFragmentList,titles);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
