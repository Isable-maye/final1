package com.swufe.final1.Adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


public class FragmentAdpter extends FragmentPagerAdapter {

    //总页面的集合
    List<Fragment> fragments;
    String[] strings = new String[]{"英译汉","汉译英","再认"};
    /**
     * 构造方法
     * @param fm
     * @param list
     */
    public FragmentAdpter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragments = list;
    }

    /**
     * 获取当前页面
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * 获取总页面数
     * @return
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 获取当前标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
