package com.dsdmsa.weather.utils

import android.support.v4.view.ViewPager

inline fun ViewPager.onPageChanged(crossinline myFunc: (pos: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            myFunc(position)
        }

    })

}

