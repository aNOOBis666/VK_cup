package com.delema.vk_cup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.delema.vk_cup.entry_screen.EntryFragment
import com.delema.vk_cup.navigation.IFragmentsNavigation
import com.delema.vk_cup.preferences_choosing_screen.PreferencesChoosingFragment

class HostActivity : AppCompatActivity(R.layout.ac_host), IFragmentsNavigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragment_container, PreferencesChoosingFragment())
            }
        }
    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }
}