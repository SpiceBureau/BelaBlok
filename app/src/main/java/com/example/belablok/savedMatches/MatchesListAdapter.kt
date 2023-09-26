package com.example.belablok.roundlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.belablok.R
import com.example.belablok.storage.Match


class MatchesListAdapter(
    private val mContext: Context,
    private val resourceLayout: Int,
    private val items: List<Match?>?
) :
    ArrayAdapter<Match?>(mContext, resourceLayout, items!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            val vi: LayoutInflater = LayoutInflater.from(mContext)
            v = vi.inflate(resourceLayout, parent, false)
        }
        val oneMatch = getItem(position)
        if (oneMatch != null) {
            val tv = v!!.findViewById<TextView>(R.id.matchTextView)
            tv.text = oneMatch.gamehands.size.toString()
        }
        return v!!
    }

    private fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE} else{
            View.VISIBLE}
    }
}