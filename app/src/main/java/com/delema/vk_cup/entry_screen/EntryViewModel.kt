package com.delema.vk_cup.entry_screen

import androidx.lifecycle.ViewModel
import com.delema.vk_cup.data_store.IPreferencesManager
import com.delema.vk_cup.data_store.PreferencesManager

class EntryViewModel: ViewModel() {

    private var preferencesManager: IPreferencesManager? = null

    init {
        preferencesManager = PreferencesManager() as? IPreferencesManager
    }

    fun onGetPreferences(): Set<String>? {
        return preferencesManager?.getPreferences()
    }
}