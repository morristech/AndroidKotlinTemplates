package com.templates.kotlintemplates.views.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup

import java.util.HashMap


abstract class BaseViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragmentHashMap = HashMap<Int, Fragment>()

    override fun getItem(i: Int): Fragment? {
        return null
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        fragmentHashMap.remove(position)
    }

    override fun getCount(): Int {
        return 0
    }

    protected fun saveFragment(key: Int, value: Fragment) {
        fragmentHashMap.put(key, value)
    }

    fun getFragment(position: Int): Fragment? {
        return fragmentHashMap[position]
    }

}
