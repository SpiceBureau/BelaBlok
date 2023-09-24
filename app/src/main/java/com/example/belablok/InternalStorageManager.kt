package com.example.belablok

import android.content.Context
import java.io.*

class InternalStorageManager(private val context: Context) {

    private val usersData = "users.txt"
    private val matchesData = "matches.txt"
    fun saveMatches(listOfMatches: List<List<GameHand>>) {
        try {
            val fileOutputStream = context.openFileOutput(matchesData, Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(listOfMatches)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMatches(): List<*> {
        try {
            val fileInputStream = context.openFileInput(matchesData)
            val objectInputStream = ObjectInputStream(fileInputStream)
            val list = objectInputStream.readObject() as List<*>
            objectInputStream.close()
            fileInputStream.close()
            return list
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList<List<GameHand>>()
    }

    fun saveUsers(listOfUsers: MutableList<Any?>) {
        try {
            val fileOutputStream = context.openFileOutput(usersData, Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(listOfUsers)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUsers(): List<*> {
        try {
            val fileInputStream = context.openFileInput(usersData)
            val objectInputStream = ObjectInputStream(fileInputStream)
            val list = objectInputStream.readObject() as List<*>
            objectInputStream.close()
            fileInputStream.close()
            return list
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList<List<User>>()
    }

}
