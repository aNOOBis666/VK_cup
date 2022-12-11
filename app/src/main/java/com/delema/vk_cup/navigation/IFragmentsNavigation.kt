package com.delema.vk_cup.navigation

import android.transition.Transition
import androidx.fragment.app.Fragment

interface IFragmentsNavigation {
//  Use entryTrans and exitTrans to set yours transition on making navigation with yours fragment
    fun openFragment(fragment: Fragment, currentFragment: Fragment? = null, transition: Transition? = null)
}