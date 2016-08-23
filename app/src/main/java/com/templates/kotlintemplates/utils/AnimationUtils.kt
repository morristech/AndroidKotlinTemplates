package com.dsdmsa.weather.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.OvershootInterpolator


fun View.rotateBack(duration: Long = 600) {
    val animSetXY = AnimatorSet()
    val scaleX = ObjectAnimator.ofFloat(this, "scaleX", 0.7f)
    val scaleY = ObjectAnimator.ofFloat(this, "scaleY", 0.7f)
    val rotationX = ObjectAnimator.ofFloat(this, "rotationX", -40f)
    val translateY = ObjectAnimator.ofFloat(this, "translationY", this.y - 100)
    scaleX.duration = duration
    scaleY.duration = duration
    rotationX.duration = duration
    translateY.duration = duration
    animSetXY.play(scaleX).with(scaleY).with(rotationX).with(translateY)
    animSetXY.interpolator = OvershootInterpolator()
    animSetXY.start()


}

fun View.restoreFromBack(duration: Long = 600) {

    val animSetXY = AnimatorSet()
    val scaleX = ObjectAnimator.ofFloat(this, "scaleX", 1f)
    val scaleY = ObjectAnimator.ofFloat(this, "scaleY", 1f)
    val rotationX = ObjectAnimator.ofFloat(this, "rotationX", 0f)
    val translateY = ObjectAnimator.ofFloat(this, "translationY", this.y + 65)
    scaleX.duration = duration
    scaleY.duration = duration
    translateY.duration = duration
    rotationX.duration = duration
    animSetXY.play(scaleX).with(scaleY).with(rotationX).with(translateY)
    animSetXY.interpolator = OvershootInterpolator()
    animSetXY.start()

}
