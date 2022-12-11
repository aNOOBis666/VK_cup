package com.delema.vk_cup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.delema.vk_cup.data_store.IPreferencesManager
import com.delema.vk_cup.data_store.PreferencesManager
import com.delema.vk_cup.navigation.IFragmentsNavigation
import com.delema.vk_cup.preferences_choosing_screen.PreferencesChoosingFragment

class HostActivity : AppCompatActivity(R.layout.ac_host), IFragmentsNavigation {
    private var preferencesManager: IPreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openFragment(PreferencesChoosingFragment())
            preferencesManager = PreferencesManager() as? IPreferencesManager
            preferencesManager?.create(applicationContext)
        }
    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}