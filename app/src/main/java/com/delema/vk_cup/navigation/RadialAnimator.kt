package com.delema.vk_cup.navigation

import android.animation.Animator
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator

class RadialAnimator() {

    companion object {
        const val PREFERENCES_CHOOSING_BUTTON_MARGIN = 400F
    }

    fun create(rootView: ViewGroup, startRadius: Float, endRadius: Float): Animator {
        return ViewAnimationUtils.createCircularReveal(
            rootView,
            rootView.width,
            0,
            startRadius,
            endRadius
        ).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
        }
    }
}