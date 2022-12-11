package com.delema.vk_cup.entry_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.delema.vk_cup.R
import com.delema.vk_cup.databinding.FmtEntryBinding
import com.delema.vk_cup.navigation.EntryTransition
import com.delema.vk_cup.navigation.IFragmentsNavigation
import com.delema.vk_cup.preferences_choosing_screen.PreferencesChoosingFragment

class EntryFragment : Fragment(R.layout.fmt_entry) {

    private var fragmentInteractor: IFragmentsNavigation? = null

    private val viewBinding by viewBinding(FmtEntryBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInteractor = activity as? IFragmentsNavigation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.later.setOnClickListener { fragmentInteractor?.openFragment(PreferencesChoosingFragment()) }
    }
}