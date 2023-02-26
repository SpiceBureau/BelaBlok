import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.belablok.Calculator
import com.example.belablok.GameRound
import com.example.belablok.R


class MyListAdapter(
    private val mContext: Context,
    private val resourceLayout: Int,
    private val items: List<GameRound>
) :
    ArrayAdapter<GameRound?>(mContext, resourceLayout, items) {
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

            tvMi.text = oneGameRound.getMiPointsSum().toString()
            tvVi.text = oneGameRound.getViPointsSum().toString()

            imPadMi.visibility = View.INVISIBLE
            imPadVi.visibility = View.INVISIBLE
            if (oneGameRound.padMi == 1){ showHide(imPadMi) }
            if (oneGameRound.padVi == 1){ showHide(imPadVi) }

            if (oneGameRound.adut == "herc"){ imAdut.setImageResource(R.drawable.herc) }
            if (oneGameRound.adut == "tref"){ imAdut.setImageResource(R.drawable.tref) }
            if (oneGameRound.adut == "kara"){ imAdut.setImageResource(R.drawable.kara) }
            if (oneGameRound.adut == "pik"){ imAdut.setImageResource(R.drawable.pik) }
        }
        return v!!
    }

    private fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE} else{
            View.VISIBLE}
    }
}