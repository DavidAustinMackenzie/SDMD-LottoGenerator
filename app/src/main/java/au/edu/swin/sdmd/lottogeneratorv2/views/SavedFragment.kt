package au.edu.swin.sdmd.lottogeneratorv2.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.sdmd.lottogeneratorv2.ui.LottoSwipeGesture
import au.edu.swin.sdmd.lottogeneratorv2.R
import au.edu.swin.sdmd.lottogeneratorv2.db.LottoRoomDatabase
import au.edu.swin.sdmd.lottogeneratorv2.models.Lotto
import au.edu.swin.sdmd.lottogeneratorv2.ui.LottoListAdaptor

// the fragment initialization parameters, e.g. currentLotto
private const val CURRENTLOTTO = "currentLotto"

/**
 * A simple [Fragment] subclass.
 * Use the [SavedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //Arguments
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_saved, container, false)
        val lottoView = view.findViewById<RecyclerView>(R.id.numberList)

        //Create Database Reference
        val db = LottoRoomDatabase(container?.context!!)

        //Get all the records from the database
        var lottoList = db.lottoDao().getGamesSortedById()
        var lottoListArray = arrayListOf<Lotto>()
        lottoListArray.addAll(lottoList)
        var adaptor = LottoListAdaptor(lottoListArray)

        //Get the current row that is being swiped and remove it
        val swipeGesture = object: LottoSwipeGesture(container?.context!!){
            override fun onSwiped(view: RecyclerView.ViewHolder,direction:Int){
                when(direction){
                    ItemTouchHelper.RIGHT ->{
                        var oldLotto = lottoList[view.adapterPosition]
                        db.lottoDao().delete(oldLotto)
                        adaptor.deleteItem(view.adapterPosition)
                        Toast.makeText(context,"Lotto Game: ${oldLotto.game} deleted!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        //Add swipe functionality to RecyclerView
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(lottoView)

        lottoView.adapter = adaptor
        lottoView.layoutManager = LinearLayoutManager(context)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SavedFragment.
         */
        @JvmStatic
        fun newInstance(newLotto: Lotto) =
            RandomFragment().apply {
                arguments = Bundle().apply {
                    //Arguments
                }
            }
    }
}