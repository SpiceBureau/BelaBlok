package com.example.belablok.roundlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.belablok.R
import com.example.belablok.storage.data_classes.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class UsersListAdapter(
    private val mContext: Context,
    private val resourceLayout: Int,
    private val items: List<User>
) :
    ArrayAdapter<User>(mContext, resourceLayout, items) {
    @OptIn(DelicateCoroutinesApi::class)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            val vi: LayoutInflater = LayoutInflater.from(mContext)
            v = vi.inflate(resourceLayout, parent, false)
        }
        val oneUser = getItem(position)
        if (oneUser != null) {
            val userImageIV = v!!.findViewById<ImageView>(R.id.userImage)
            val userNameTV = v.findViewById<TextView>(R.id.userName)
            val userName = oneUser.name

            userNameTV.text = userName
            val imageUrl = "https://api.dicebear.com/7.x/initials/png?seed=$userName?size=16"

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val imageData = fetchImageData(imageUrl)
                    launch(Dispatchers.Main) {
                        if (imageData != null) {
                            Glide.with(context)
                                .load(imageData)
                                .into(userImageIV)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return v!!
    }

    private fun fetchImageData(url: String): ByteArray? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                return response.body?.bytes()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}