package com.delema.vk_cup

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.delema.vk_cup.data_store.IPreferencesManager
import com.delema.vk_cup.entry_screen.EntryFragment
import com.delema.vk_cup.navigation.IFragmentsNavigation
import com.delema.vk_cup.preferences_choosing_screen.PreferencesChoosingFragment

class HostActivity : AppCompatActivity(R.layout.ac_host), IFragmentsNavigation,
    IPreferencesManager {

    companion object {
        private const val STORAGE_NAME = "vk_cup_storage"
        private const val SAVED_PREFERENCES = "saved_preferences"
        private const val SAVED_IS_FIRST_LAUNCH = "saved_is_first_launch"
    }

    private var prefsManager: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            prefsManager = applicationContext.getSharedPreferences(STORAGE_NAME, MODE_PRIVATE)
            setStartDestination()
        }
    }

    private fun setStartDestination() {
        if (getIsFirstLaunch() == true) openFragment(PreferencesChoosingFragment())
        else openFragment(EntryFragment())
    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun savePreferences(value: Set<String>) {
        prefsManager?.edit()?.putStringSet(SAVED_PREFERENCES, value)?.apply()
    }

    override fun getPreferences(): Set<String>? {
        return prefsManager?.getStringSet(SAVED_PREFERENCES, null)
    }

    override fun saveIsFirstLaunch() {
        prefsManager?.edit()?.putBoolean(SAVED_IS_FIRST_LAUNCH, false)?.apply()
    }

    override fun getIsFirstLaunch(): Boolean? {
        return prefsManager?.getBoolean(SAVED_IS_FIRST_LAUNCH, true)
    }
}