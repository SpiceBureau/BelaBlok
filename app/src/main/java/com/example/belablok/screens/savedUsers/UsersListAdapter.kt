package com.example.belablok.roundlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.belablok.R
import com.example.belablok.storage.data_classes.User


class UsersListAdapter(
    private val mContext: Context,
    private val resourceLayout: Int,
    private val items: List<User>
) :
    ArrayAdapter<User>(mContext, resourceLayout, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            val vi: LayoutInflater = LayoutInflater.from(mContext)
            v = vi.inflate(resourceLayout, parent, false)
        }
        val oneUser = getItem(position)
        if (oneUser != null) {
            val userImage = v!!.findViewById<ImageView>(R.id.userImage)

            val userName = oneUser.name
            val apiUrl = "https://avatars.dicebear.com/api/initials/$userName.svg"
        }
        return v!!
    }
}