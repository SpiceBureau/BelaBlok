import android.content.Context
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.belablok.R

class UserSpinnerAdapter(context: Context, resource: Int, private val items: List<String?>) :
    ArrayAdapter<String>(context, resource, items) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.user_spinner_item, parent, false)

        val iconImageView = view.findViewById<ImageView>(R.id.spinner_item_icon)
        val textTextView = view.findViewById<TextView>(R.id.spinner_item_text)

        if (position == 0) { iconImageView.setImageResource(R.drawable.arrow_down) }
        else { iconImageView.visibility = View.INVISIBLE }

        textTextView.text = items[position]

        return view
    }
}
