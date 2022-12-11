package com.delema.vk_cup.preferences_choosing_screen

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.delema.vk_cup.R
import com.delema.vk_cup.databinding.FmtPreferencesChoosingBinding
import com.delema.vk_cup.entry_screen.EntryFragment
import com.delema.vk_cup.navigation.EntryTransition
import com.delema.vk_cup.navigation.IFragmentsNavigation
import com.delema.vk_cup.preferences_choosing_screen.adapter.PreferencesChoosingAdapter
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class PreferencesChoosingFragment : Fragment(R.layout.fmt_preferences_choosing) {

    private var fragmentInteractor: IFragmentsNavigation? = null

    private val viewBinding by viewBinding(FmtPreferencesChoosingBinding::bind)
    private val prefsChoosingAdapter = PreferencesChoosingAdapter(::onClickItem)
    private val entryTransition = EntryTransition()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInteractor = activity as? IFragmentsNavigation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        with(viewBinding) {
            later.setOnClickListener {
                fragmentInteractor?.openFragment(EntryFragment(), this@PreferencesChoosingFragment, entryTransition)
            }
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