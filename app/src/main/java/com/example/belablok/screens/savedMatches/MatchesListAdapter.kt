package com.example.belablok.roundlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.belablok.R
import com.example.belablok.storage.data_classes.Match


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
            val player1 = v!!.findViewById<TextView>(R.id.player1)
            val player2 = v.findViewById<TextView>(R.id.player2)
            val player3 = v.findViewById<TextView>(R.id.player3)
            val player4 = v.findViewById<TextView>(R.id.player4)

            player1.text = oneMatch.player1?.name ?: ""
            player2.text = oneMatch.player2?.name ?: ""
            player3.text = oneMatch.player3?.name ?: ""
            player4.text = oneMatch.player4?.name ?: ""
        }
        return v!!
    }

    private fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE} else{
            View.VISIBLE}
    }
}