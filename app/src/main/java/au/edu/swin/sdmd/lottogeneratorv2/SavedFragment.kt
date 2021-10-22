package au.edu.swin.sdmd.lottogeneratorv2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.TypeVariable
import kotlin.random.Random

// the fragment initialization parameters, e.g. currentLotto
private const val CURRENTLOTTO = "currentLotto"

/**
 * A simple [Fragment] subclass.
 * Use the [SavedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedFragment : Fragment() {
    private var currentLotto = Lotto(0,0,0,0
        ,0,0,0,0,0)
    private var lottoList = ArrayList<Lotto>()

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

        //Get the Current Game Lotto from Shared Preferences
        currentLotto = checkSharedPrefs()
        lottoList.add(currentLotto)
        lottoView.adapter = LottoAdaptor(lottoList)
        lottoView.layoutManager = LinearLayoutManager(container?.context)

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

    //Used to retrieve previously saved Lotto Game
    private fun checkSharedPrefs():Lotto{
        currentLotto = Lotto(0,0,0,0,0,0,
            0,0,0)
        val sharedPref = this.context?.getSharedPreferences("SAVEDGAME",
            Context.MODE_PRIVATE)
        sharedPref?.let{
            currentLotto.num1 = sharedPref.getInt("num1",0)
            currentLotto.num2 = sharedPref.getInt("num2",0)
            currentLotto.num3 = sharedPref.getInt("num3",0)
            currentLotto.num4 = sharedPref.getInt("num4",0)
            currentLotto.num5 = sharedPref.getInt("num5",0)
            currentLotto.num6 = sharedPref.getInt("num6",0)
            currentLotto.num7 = sharedPref.getInt("num7",0)
            currentLotto.pow1 = sharedPref.getInt("pow1",0)
        }
        return currentLotto
    }

}