package com.delema.vk_cup.data_store

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class PreferencesManager: IPreferencesManager {

    companion object {
        private const val STORAGE_NAME = "vk_cup_storage"
        private const val SAVED_PREFERENCES = "saved_preferences"
    }

    private var prefsManager: SharedPreferences? = null

    override fun create(context: Context) {
        prefsManager = context.getSharedPreferences(STORAGE_NAME, MODE_PRIVATE)
    }

    override fun savePreferences(value: Set<String>) {
        prefsManager?.edit()?.putStringSet(SAVED_PREFERENCES, value)?.apply()
    }

    override fun getPreferences(): Set<String>? {
        return prefsManager?.getStringSet(SAVED_PREFERENCES, null)
    }
}