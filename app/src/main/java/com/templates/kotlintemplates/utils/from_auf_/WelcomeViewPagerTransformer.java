package com.templates.kotlintemplates.utils.from_auf_;//package com.templates.javatemplates.utils.from_auf_;
//
//import android.support.v4.view.ViewPager;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.aufshaus.R;
//import com.aufshaus.views.TypefaceTextView;
//
//public class WelcomeViewPagerTransformer implements ViewPager.PageTransformer {
//
//
//    @Override
//    public void transformPage(View page, float position) {
//        int pageWidth = page.getWidth();
//        final float delta = Math.abs(Math.abs(position) - 1);
//        TypefaceTextView text1 = (TypefaceTextView) page.findViewById(R.id.text1);
//        TypefaceTextView text2 = (TypefaceTextView) page.findViewById(R.id.text2);
//        ImageView logoImage = (ImageView) page.findViewById(R.id.logo);
//        ImageView background = (ImageView) page.findViewById(R.id.background);
//
//        if (position <= 1) {
//            if (background != null) {
//                background.setTranslationX(-position * (pageWidth / 2));
//            }
//            if (logoImage != null) {
//                logoImage.setAlpha(delta);
//                logoImage.setTranslationX(position * (pageWidth / 2));
//            }
//            if (text1 != null) {
//                text1.setAlpha(delta);
//                text1.setTranslationX(position * (pageWidth / 6));
//            }
//            if (text2 != null) {
//                text2.setAlpha(delta);
//                text2.setTranslationX(position * (pageWidth / 12));
//            }
//        }
//    }
//}
