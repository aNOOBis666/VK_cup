package com.delema.vk_cup.preferences_choosing_screen

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.delema.vk_cup.R
import com.delema.vk_cup.databinding.FmtPreferencesChoosingBinding
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

    private val viewBinding by viewBinding(FmtPreferencesChoosingBinding::bind)
    private val prefsChoosingAdapter = PreferencesChoosingAdapter(::onClickItem)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInteractor = activity as? IFragmentsNavigation
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        return if (!enter) {
            RadialAnimator().create(
                viewBinding.root,
                hypot(
                    viewBinding.root.width.toDouble(),
                    viewBinding.root.height.toDouble()
                ).toFloat(),
                hypot(
                    viewBinding.root.width.toDouble(),
                    viewBinding.root.height.toDouble()
                ).toFloat()
            )
        } else null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.root.doOnLayout {
            RadialAnimator().create(
                viewBinding.root,
                PREFERENCES_CHOOSING_BUTTON_MARGIN,
                hypot(
                    viewBinding.root.width.toDouble(),
                    viewBinding.root.height.toDouble()
                ).toFloat()
            ).start()
        }
        initAdapter()

        with(viewBinding) {
            later.setOnClickListener { fragmentInteractor?.openFragment(EntryFragment()) }
            submit.setOnClickListener { fragmentInteractor?.openFragment(EntryFragment()) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.categories.adapter = null
    }

    private fun initAdapter() = with(viewBinding) {
        categories.adapter = prefsChoosingAdapter
        categories.layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.FLEX_START
        }
        prefsChoosingAdapter.submitList(
            resources.getStringArray(R.array.preferences_types).toList()
        )
    }

    private fun onClickItem() {
        viewBinding.submit.isEnabled = prefsChoosingAdapter.getChangedItems().isNotEmpty()
    }
}