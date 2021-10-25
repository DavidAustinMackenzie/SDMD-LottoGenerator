package au.edu.swin.sdmd.lottogeneratorv2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        val lottoList = db.lottoDao().getGamesSortedById()
        lottoList.forEach{
            Log.i("LOTTO_ID",it.id.toString())
        }
        lottoView.adapter = LottoListAdaptor(lottoList)
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