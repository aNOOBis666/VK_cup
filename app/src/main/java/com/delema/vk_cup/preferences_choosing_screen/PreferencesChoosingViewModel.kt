package com.delema.vk_cup.preferences_choosing_screen

import androidx.lifecycle.ViewModel
import com.delema.vk_cup.data_store.IPreferencesManager
import com.delema.vk_cup.data_store.PreferencesManager

class PreferencesChoosingViewModel: ViewModel() {

    private var preferencesManager: IPreferencesManager? = null

    init {
        preferencesManager = PreferencesManager() as? IPreferencesManager
    }

    fun onSavePreferences(preferencesSet: Set<String>) {
        preferencesManager?.savePreferences(preferencesSet)
    }

    fun onGetPreferences(): Set<String>? {
        return preferencesManager?.getPreferences()
    }
}