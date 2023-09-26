package com.example.belablok.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MatchStorage(context: Context) {
    private val preferences: SharedPreferences
    private val gson: Gson

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        gson = Gson()
    }

    fun saveData(data: List<Match?>?) {
        val dataJson = gson.toJson(data)
        preferences.edit().putString(DATA_KEY, dataJson).apply()
    }

    fun loadData(): List<Match?>? {
        val dataJson = preferences.getString(DATA_KEY, null)
        return if (dataJson != null) {
            val dataType = object : TypeToken<List<Match?>?>() {}.type
            gson.fromJson(dataJson, dataType)
        } else {
            ArrayList()
        }
    }

    companion object {
        private const val PREF_NAME = "MatchesData"
        private const val DATA_KEY = "matches_data_key"
    }
}
