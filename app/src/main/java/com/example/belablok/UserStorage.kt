package com.example.belablok

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class UserStorage(context: Context) {
    private val preferences: SharedPreferences
    private val gson: Gson

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        gson = Gson()
    }

    fun saveData(data: List<User?>?) {
        val dataJson = gson.toJson(data)
        preferences.edit().putString(DATA_KEY, dataJson).apply()
    }

    fun loadData(): List<User> {
        val dataJson = preferences.getString(DATA_KEY, null)
        return if (dataJson != null) {
            val dataType = object : TypeToken<List<User?>?>() {}.type
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
