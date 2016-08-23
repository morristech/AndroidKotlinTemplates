package com.templates.kotlintemplates.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;


public abstract class BaseViewPagerAdapter extends FragmentStatePagerAdapter {

    private Map<Integer, Fragment> fragmentHashMap = new HashMap<>();

    public BaseViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragmentHashMap.remove(position);
    }

    @Override
    public int getCount() {
        return 0;
    }

    protected void saveFragment(Integer key, Fragment value) {
        fragmentHashMap.put(key, value);
    }

    public Fragment getFragment(int position) {
        return fragmentHashMap.get(position);
    }

}
