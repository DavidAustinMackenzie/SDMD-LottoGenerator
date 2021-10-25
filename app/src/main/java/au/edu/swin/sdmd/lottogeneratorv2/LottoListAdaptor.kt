package au.edu.swin.sdmd.lottogeneratorv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LottoListAdaptor(private val lottoList:List<Lotto>) :
    RecyclerView.Adapter<LottoListAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            LottoListAdaptor.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.layout_row, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LottoListAdaptor.ViewHolder, position: Int) {
        val item = lottoList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = lottoList.size

    inner class ViewHolder(private val v:View): RecyclerView.ViewHolder(v){
        //Get elements to display for each row
        val num1 = v.findViewById<TextView>(R.id.txtNum1)
        val num2 = v.findViewById<TextView>(R.id.txtNum2)
        val num3 = v.findViewById<TextView>(R.id.txtNum3)
        val num4 = v.findViewById<TextView>(R.id.txtNum4)
        val num5 = v.findViewById<TextView>(R.id.txtNum5)
        val num6 = v.findViewById<TextView>(R.id.txtNum6)
        val num7 = v.findViewById<TextView>(R.id.txtNum7)
        val num8 = v.findViewById<TextView>(R.id.txtNum8)
        val txtRowHeading = v.findViewById<TextView>(R.id.txtRowHeading)

        fun bind(item: Lotto){
            txtRowHeading.text = "Lotto Game: " + item.id
            num1.text = item.num1.toString()
            num2.text = item.num2.toString()
            num3.text = item.num3.toString()
            num4.text = item.num4.toString()
            num5.text = item.num5.toString()
            num6.text = item.num6.toString()
            num7.text = item.num7.toString()
            num8.text = item.pow1.toString()
        }
    }
}