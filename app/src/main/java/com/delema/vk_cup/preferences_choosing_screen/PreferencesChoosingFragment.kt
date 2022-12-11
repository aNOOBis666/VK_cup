package com.delema.vk_cup.preferences_choosing_screen

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.delema.vk_cup.R
import com.delema.vk_cup.data_store.IPreferencesManager
import com.delema.vk_cup.entry_screen.EntryFragment
import com.delema.vk_cup.navigation.IFragmentsNavigation
import com.delema.vk_cup.navigation.RadialAnimator
import com.delema.vk_cup.navigation.RadialAnimator.Companion.PREFERENCES_CHOOSING_BUTTON_MARGIN
import com.delema.vk_cup.preferences_choosing_screen.adapter.PreferencesChoosingAdapter
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlin.math.hypot

class PreferencesChoosingFragment : Fragment(R.layout.fmt_preferences_choosing) {

    private var fragmentInteractor: IFragmentsNavigation? = null
    private var preferencesManager: IPreferencesManager? = null

    private val prefsChoosingAdapter = PreferencesChoosingAdapter(::onClickItem)

    private var root: ConstraintLayout? = null
    private var later: TextView? = null
    private var submit: AppCompatButton? = null
    private var categories: RecyclerView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInteractor = activity as? IFragmentsNavigation
        preferencesManager = activity as? IPreferencesManager
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
        initAdapter()

        submit?.isEnabled = prefsChoosingAdapter.getChangedItems().isNotEmpty()
        later?.setOnClickListener { fragmentInteractor?.openFragment(EntryFragment()) }
        submit?.setOnClickListener {
            preferencesManager?.saveIsFirstLaunch()
            preferencesManager?.savePreferences(prefsChoosingAdapter.getChangedItems().toSet())
            fragmentInteractor?.openFragment(EntryFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        categories?.adapter = null
    }

    private fun initViews() {
        root = view?.findViewById(R.id.root)
        later = view?.findViewById(R.id.later)
        submit = view?.findViewById(R.id.submit)
        categories = view?.findViewById(R.id.categories)
    }

    private fun initAdapter() {
        categories?.adapter = prefsChoosingAdapter
        categories?.layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.FLEX_START
        }
        prefsChoosingAdapter.submitList(
            resources.getStringArray(R.array.preferences_types).toList()
        )
    }

    private fun onClickItem() {
        submit?.isEnabled = prefsChoosingAdapter.getChangedItems().isNotEmpty()
    }
}