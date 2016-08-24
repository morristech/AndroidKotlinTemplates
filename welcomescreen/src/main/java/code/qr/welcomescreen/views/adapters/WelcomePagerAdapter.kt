package com.mapsocial.socialmap.views.adapters

import android.content.Context
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mapsocial.socialmap.R


class WelcomePagerAdapter : PagerAdapter() {

    override fun getCount(): Int {
        return 3
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var resId = 0
        when (position) {
            0 -> resId = R.layout.welcome_page_one
            1 -> resId = R.layout.welcome_page_two
            2 -> resId = R.layout.welcome_page_three
        }

        val view = inflater.inflate(resId, null)
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(view: ViewGroup, position: Int, proxy: Any) {
        view.removeView(proxy as View)
    }


    override fun isViewFromObject(view: View, proxy: Any): Boolean {
        return view === proxy

    }

    override fun saveState(): Parcelable? {
        return null
    }
}