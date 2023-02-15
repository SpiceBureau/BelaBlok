import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
            v = vi.inflate(resourceLayout, null)
        }
        val oneGameRound = getItem(position)
        if (oneGameRound != null) {
            val tvMi = v!!.findViewById<View>(R.id.tvMi) as TextView
            val tvVi = v.findViewById<View>(R.id.tvVi) as TextView
            val tvDelta = v.findViewById<View>(R.id.tvDelta) as TextView

            tvMi.text = oneGameRound.miPoints
            tvVi.text = oneGameRound.viPoints
            if (position == items.size - 1) { tvDelta.text = "Δ = ".plus(oneGameRound.delta.toString()) }
            else{ tvDelta.text = "" }
        }
        return v!!
    }
}