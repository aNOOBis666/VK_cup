package com.delema.vk_cup.entry_screen

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.delema.vk_cup.R
import com.delema.vk_cup.navigation.IFragmentsNavigation
import com.delema.vk_cup.navigation.RadialAnimator
import com.delema.vk_cup.navigation.RadialAnimator.Companion.PREFERENCES_CHOOSING_BUTTON_MARGIN
import com.delema.vk_cup.preferences_choosing_screen.PreferencesChoosingFragment
import com.delema.vk_cup.preferences_choosing_screen.PreferencesChoosingViewModel
import kotlin.math.hypot

class EntryFragment: Fragment(R.layout.fmt_entry) {

    private var fragmentInteractor: IFragmentsNavigation? = null
    private var entryViewModel: EntryViewModel? = null


    private var root: ConstraintLayout? = null
    private var later: TextView? = null
    private var title: TextView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInteractor = activity as? IFragmentsNavigation
        entryViewModel = ViewModelProvider(this)[EntryViewModel::class.java]
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        return if (!enter) {
            root?.let { rootView ->
                RadialAnimator().create(
                    rootView,
                    hypot(rootView.width.toDouble(), rootView.height.toDouble()).toFloat(),
                    hypot(rootView.width.toDouble(), rootView.height.toDouble()).toFloat()
                )
            }
        } else null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        root?.doOnLayout {
            root?.let { rootView ->
                RadialAnimator().create(
                    rootView,
                    PREFERENCES_CHOOSING_BUTTON_MARGIN,
                    hypot(rootView.width.toDouble(), rootView.height.toDouble()).toFloat()
                ).start()
            }
        }
        title?.text = entryViewModel?.onGetPreferences().toString()
        later?.setOnClickListener { fragmentInteractor?.openFragment(PreferencesChoosingFragment()) }
    }

    private fun initViews() {
        root = view?.findViewById(R.id.root)
        later = view?.findViewById(R.id.later)
        title = view?.findViewById(R.id.title)
    }
}