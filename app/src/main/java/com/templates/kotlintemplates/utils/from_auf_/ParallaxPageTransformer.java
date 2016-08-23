package com.templates.kotlintemplates.utils.from_auf_;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.templates.javatemplates.R;


public class ParallaxPageTransformer implements ViewPager.PageTransformer {
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(1);
        } else if (position <= 1) { // [-1,1]
//            if (recyclerView != null) {
//                recyclerView.setTranslationX(-position * (pageWidth / 2)); //Half the normal speed
//            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(1);
        }
    }
}
