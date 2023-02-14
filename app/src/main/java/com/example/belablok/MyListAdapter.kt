import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.belablok.GameRound
import com.example.belablok.R


class MyListAdapter(
    private val mContext: Context,
    private val resourceLayout: Int,
    private val items: List<GameRound>
) :
    ArrayAdapter<GameRound?>(mContext, resourceLayout, items!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            val vi: LayoutInflater = LayoutInflater.from(mContext)
            v = vi.inflate(resourceLayout, null)
        }
        val onGameRound = getItem(position)
        if (onGameRound != null) {
            val tvMi = v!!.findViewById<View>(R.id.tvMi) as TextView
            val tvVi = v.findViewById<View>(R.id.tvVi) as TextView

            tvMi.text = onGameRound.miPoints.toString()
            tvVi.text = onGameRound.viPoints.toString()
        }
        return v!!
    }
}