package com.example.belablok.roundlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import com.example.belablok.storage.GameHand
import com.example.belablok.R


class MyListAdapter(
    private val mContext: Context,
    private val resourceLayout: Int,
    private val items: List<GameHand>
) :
    ArrayAdapter<GameHand?>(mContext, resourceLayout, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            val vi: LayoutInflater = LayoutInflater.from(mContext)
            v = vi.inflate(resourceLayout, parent, false)
        }
        val oneGameRound = getItem(position)
        if (oneGameRound != null) {
            val tvMi = v!!.findViewById<View>(R.id.tvMi) as TextView
            val tvVi = v.findViewById<View>(R.id.tvVi) as TextView
            val imPadMi = v.findViewById<View>(R.id.imPadMi) as ImageView
            val imPadVi = v.findViewById<View>(R.id.imPadVi) as ImageView
            val imAdut = v.findViewById<View>(R.id.imAdut) as ImageView
            val imStiljaMi = v.findViewById<View>(R.id.imStiljaMi) as ImageView
            val imStiljaVi = v.findViewById<View>(R.id.imStlijaVi) as ImageView
            val viewLine = v.findViewById<View>(R.id.vLine) as View

            tvMi.setTextColor(Color.parseColor("#ffffff"))
            tvVi.setTextColor(Color.parseColor("#ffffff"))

            imStiljaMi.visibility = View.INVISIBLE
            imStiljaVi.visibility = View.INVISIBLE
            imPadMi.visibility = View.INVISIBLE
            imPadVi.visibility = View.INVISIBLE
            viewLine.visibility = View.INVISIBLE


            if (oneGameRound.matchPointsListItemFlag){
                tvMi.text = oneGameRound.miMatchPoints.toString()
                tvMi.setTextAppearance(R.style.boldText)
                tvMi.setTextColor(Color.parseColor("#03dac6"))
                tvMi.textSize = 60.0F


                tvVi.text = oneGameRound.viMatchPoints.toString()
                tvVi.setTextAppearance(R.style.boldText)
                tvVi.setTextColor(Color.parseColor("#da6b03"))
                tvVi.textSize = 60.0F

                imAdut.setImageResource(0)
                viewLine.visibility = View.VISIBLE
            }
            else{
                tvMi.text = oneGameRound.getMiPointsSum().toString()
                tvVi.text = oneGameRound.getViPointsSum().toString()

                tvMi.textSize = 50.0F
                tvVi.textSize = 50.0F

                tvMi.setTextAppearance(0)
                tvVi.setTextAppearance(0)

                if (oneGameRound.padMi == 1){ showHide(imPadMi) }
                if (oneGameRound.padVi == 1){ showHide(imPadVi) }

                if (oneGameRound.stiljaMi == 1){ showHide(imStiljaMi) }
                if (oneGameRound.stiljaVi == 1){ showHide(imStiljaVi) }

                if (oneGameRound.adut == "herc"){ imAdut.setImageResource(R.drawable.herc) }
                if (oneGameRound.adut == "tref"){ imAdut.setImageResource(R.drawable.tref) }
                if (oneGameRound.adut == "kara"){ imAdut.setImageResource(R.drawable.kara) }
                if (oneGameRound.adut == "pik"){ imAdut.setImageResource(R.drawable.pik) }
            }
        }
        return v!!
    }

    private fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE} else{
            View.VISIBLE}
    }
}