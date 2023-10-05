package com.example.belablok.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.belablok.storage.data_classes.Player
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class UserStorage(context: Context) {
    private val preferences: SharedPreferences
    private val gson: Gson

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        gson = Gson()
    }

    fun saveData(data: List<Player?>?) {
        val dataJson = gson.toJson(data)
        preferences.edit().putString(DATA_KEY, dataJson).apply()
    }

    fun loadData(): List<Player> {
        val dataJson = preferences.getString(DATA_KEY, null)
        return if (dataJson != null) {
            val dataType = object : TypeToken<List<Player?>?>() {}.type
            gson.fromJson(dataJson, dataType)
        } else {
            ArrayList()
        }
    }

    companion object {
        private const val PREF_NAME = "UsersData"
        private const val DATA_KEY = "users_data_key"
    }
}
