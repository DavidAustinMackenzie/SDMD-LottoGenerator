package au.edu.swin.sdmd.lottogeneratorv2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UpdateActivityDialogFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.dialog_fragment_update, container, false)

/*        //Get the value passed as an argument to this Fragment
        val olympic = this.arguments?.getParcelable<Olympic>("OLYMPIC")

        //Get all the variables passed in from Parcelable object
        olympic?.let {
            val txtCountry = view.findViewById<TextView>(R.id.txtFragmentCountry)
            txtCountry.text = it.country

            val txtCode = view.findViewById<TextView>(R.id.txtFragmentCode)
            txtCode.text = it.code

            val txtGold = view.findViewById<TextView>(R.id.txtFragmentGold)
            txtGold.text = "${it.gold} gold medals"
            txtGold.setTextColor(Color.rgb(255,215,0))

            val txtSilver = view.findViewById<TextView>(R.id.txtFragmentSilver)
            txtSilver.text = "${it.silver} silver medals"
            txtSilver.setTextColor(Color.rgb(192,192,192))

            val txtBronze = view.findViewById<TextView>(R.id.txtFragmentBronze)
            txtBronze.text =  "${it.bronze} bronze medals"
            txtBronze.setTextColor(Color.rgb(80,50,20))
        }*/

        return view
    }
}