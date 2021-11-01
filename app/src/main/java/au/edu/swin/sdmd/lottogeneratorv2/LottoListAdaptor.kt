package au.edu.swin.sdmd.lottogeneratorv2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class LottoListAdaptor(private val lottoList:ArrayList<Lotto>) :
    RecyclerView.Adapter<LottoListAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            LottoListAdaptor.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.layout_row, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LottoListAdaptor.ViewHolder, position: Int)
    = holder.bind(lottoList[position])

    override fun getItemCount(): Int = lottoList.size

    inner class ViewHolder(private val itemView:View): RecyclerView.ViewHolder(itemView){
        //Get elements to display for each row
        val num1 = itemView.findViewById<TextView>(R.id.txtNum1)
        val num2 = itemView.findViewById<TextView>(R.id.txtNum2)
        val num3 = itemView.findViewById<TextView>(R.id.txtNum3)
        val num4 = itemView.findViewById<TextView>(R.id.txtNum4)
        val num5 = itemView.findViewById<TextView>(R.id.txtNum5)
        val num6 = itemView.findViewById<TextView>(R.id.txtNum6)
        val num7 = itemView.findViewById<TextView>(R.id.txtNum7)
        val num8 = itemView.findViewById<TextView>(R.id.txtNum8)
        val txtRowHeading = itemView.findViewById<TextView>(R.id.txtRowHeading)

        fun bind(item: Lotto) = with(itemView) {
            txtRowHeading.text = "Lotto Game: " + item.game
            num1.text = item.num1.toString()
            num2.text = item.num2.toString()
            num3.text = item.num3.toString()
            num4.text = item.num4.toString()
            num5.text = item.num5.toString()
            num6.text = item.num6.toString()
            num7.text = item.num7.toString()
            num8.text = item.pow1.toString()

            itemView.setOnClickListener {
                var activity = it.context as AppCompatActivity
                var updateActivityDialogFragment = UpdateActivityDialogFragment()
                updateActivityDialogFragment.show(activity.supportFragmentManager, "TAG")
                var bundle = Bundle()
                bundle.putInt("LOTTO_ID", item.id)
                updateActivityDialogFragment.arguments = bundle
            }
        }
    }

    //Remove the item from the RecyclerView and refresh
    fun deleteItem(i:Int){
        lottoList.removeAt(i)
        notifyDataSetChanged()
    }
}