package com.templates.kotlintemplates.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils

/**
 * Created by Ricardo Lecheta on 28/12/2014.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
object RevealEffect {

    fun show(view: View, animDuration: Long) {
        // Centro da view
        val cx = (view.left + view.right) / 2
        val cy = (view.top + view.bottom) / 2

        // Define o arco para a animação
        val finalRadius = Math.max(view.width, view.height)

        // Cria a animação
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())

        // Inicia a animação
        view.visibility = View.VISIBLE
        anim.duration = animDuration
        anim.start()
    }

    fun hide(view: View, animDuration: Long) {
        // Centro da view
        val cx = (view.left + view.right) / 2
        val cy = (view.top + view.bottom) / 2

        // Define o arco para a animação
        val initialRadius = view.width

        // Cria a animação
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius.toFloat(), 0f)

        // Quando a animação terminar, esconde a view
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })

        // Inicia a animação
        anim.duration = animDuration
        anim.start()
    }
}
