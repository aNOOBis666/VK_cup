package com.delema.vk_cup.navigation

import android.animation.Animator
import android.transition.Transition
import android.transition.TransitionValues
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import kotlin.math.hypot

class ExitTransition : Transition() {

    override fun captureStartValues(transitionValues: TransitionValues) {}

    override fun captureEndValues(transitionValues: TransitionValues) {}

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        return ViewAnimationUtils.createCircularReveal(
            sceneRoot,
            sceneRoot.width,
            0,
            hypot(sceneRoot.width.toDouble(), sceneRoot.height.toDouble()).toFloat(),
            200F
        ).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
        }
    }
}