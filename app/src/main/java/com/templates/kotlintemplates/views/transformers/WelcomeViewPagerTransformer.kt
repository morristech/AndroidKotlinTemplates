package com.templates.kotlintemplates.views.transformers


import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView

import com.mapsocial.socialmap.R

class WelcomeViewPagerTransformer : ViewPager.PageTransformer {


    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val delta = Math.abs(Math.abs(position) - 1)
        val background = page.findViewById(R.id.background) as ImageView

        if (position <= 1) {
            if (background != null) {
                background.translationX = -position * (pageWidth / 2)
            }
//            if (logoImage != null) {
//                logoImage.alpha = delta
//                logoImage.translationX = position * (pageWidth / 2)
//            }
//            if (text1 != null) {
//                text1!!.setAlpha(delta)
//                text1!!.setTranslationX(position * (pageWidth / 6))
//            }
//            if (text2 != null) {
//                text2!!.setAlpha(delta)
//                text2!!.setTranslationX(position * (pageWidth / 12))
//            }
        }
    }
}
