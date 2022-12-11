package com.delema.vk_cup.data_store

interface IPreferencesManager {
    fun savePreferences(value: Set<String>)
    fun getPreferences(): Set<String>?

    fun saveIsFirstLaunch()
    fun getIsFirstLaunch(): Boolean?
}