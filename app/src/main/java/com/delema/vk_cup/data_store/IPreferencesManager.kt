package com.delema.vk_cup.data_store

import android.content.Context

interface IPreferencesManager {
    fun create(context: Context)
    fun savePreferences(value: Set<String>)
    fun getPreferences(): Set<String>?
}