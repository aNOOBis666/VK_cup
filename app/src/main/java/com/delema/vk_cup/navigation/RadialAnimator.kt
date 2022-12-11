package com.delema.vk_cup.navigation

import android.animation.Animator
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator

class RadialAnimator {

    companion object {
        const val PREFERENCES_CHOOSING_BUTTON_MARGIN = 400F
        const val DEFAULT_CENTER_Y = 0
        const val DEFAULT_DURATION = 1000L
    }

    fun create(rootView: ViewGroup, startRadius: Float, endRadius: Float): Animator {
        return ViewAnimationUtils.createCircularReveal(
            rootView,
            rootView.width,
            DEFAULT_CENTER_Y,
            startRadius,
            endRadius
        ).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = DEFAULT_DURATION
        }
    }
}