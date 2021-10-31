package au.edu.swin.sdmd.lottogeneratorv2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

// the fragment initialization parameters, e.g. currentLotto
private const val CURRENTLOTTO = "currentLotto"

/**
 * A simple [Fragment] subclass.
 * Use the [RandomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RandomFragment : Fragment() {
    private lateinit var currentLotto: Lotto
    private lateinit var txtNum1: TextView
    private lateinit var txtNum2: TextView
    private lateinit var txtNum3: TextView
    private lateinit var txtNum4: TextView
    private lateinit var txtNum5: TextView
    private lateinit var txtNum6: TextView
    private lateinit var txtNum7: TextView
    private lateinit var txtNum8: TextView
    private lateinit var lottoList: List<Lotto>


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

        val view = inflater.inflate(R.layout.fragment_random,container,false)
        //Create Database Reference
        val db = LottoRoomDatabase(container?.context!!)
        //Get the current list of Lotto Games
        lottoList = db.lottoDao().getGamesSortedById()

        //Used to check if the current game has been saved in Shared Preferences if
        //there is at least 1 item in the list
        if(lottoList.isNotEmpty()){
            //Get the current lotto game details if they exist from SharedPreferences
            currentLotto = checkSharedPrefs()
        }else{
            currentLotto = Lotto(0,0,0,0,0,0,0,0,0)
        }

        //Get input elements
        val btnRandom = view.findViewById<Button>(R.id.btnRandom)
        val btnReset = view.findViewById<Button>(R.id.btnReset)
        txtNum1 = view.findViewById<TextView>(R.id.txtRandomNum1)
        txtNum2 = view.findViewById<TextView>(R.id.txtRandomNum2)
        txtNum3 = view.findViewById<TextView>(R.id.txtRandomNum3)
        txtNum4 = view.findViewById<TextView>(R.id.txtRandomNum4)
        txtNum5 = view.findViewById<TextView>(R.id.txtRandomNum5)
        txtNum6 = view.findViewById<TextView>(R.id.txtRandomNum6)
        txtNum7 = view.findViewById<TextView>(R.id.txtRandomNum7)
        txtNum8 = view.findViewById<TextView>(R.id.txtRandomNum8)

        //Assign Shared Preferences from Saved Game
        txtNum1.text = currentLotto.num1.toString()
        txtNum2.text = currentLotto.num2.toString()
        txtNum3.text = currentLotto.num3.toString()
        txtNum4.text = currentLotto.num4.toString()
        txtNum5.text = currentLotto.num5.toString()
        txtNum6.text = currentLotto.num6.toString()
        txtNum7.text = currentLotto.num7.toString()
        txtNum8.text = currentLotto.pow1.toString()

        //Used to generate a new lotto game and display on fragment
        btnRandom.setOnClickListener{
            val newLotto = getRandomLottoGame()
            //Insert value into the database

            //Display results in Random Fragment
            txtNum1.text = newLotto.num1.toString()
            txtNum2.text = newLotto.num2.toString()
            txtNum3.text = newLotto.num3.toString()
            txtNum4.text = newLotto.num4.toString()
            txtNum5.text = newLotto.num5.toString()
            txtNum6.text = newLotto.num6.toString()
            txtNum7.text = newLotto.num7.toString()
            txtNum8.text = newLotto.pow1.toString()

            //Use the values to be passed to SavedFragment
            val sharedPref = container?.context?.getSharedPreferences("SAVEDGAME",
                Context.MODE_PRIVATE) ?: return@setOnClickListener
            with(sharedPref.edit()){
                putInt("num1",newLotto.num1)
                putInt("num2",newLotto.num2)
                putInt("num3",newLotto.num3)
                putInt("num4",newLotto.num4)
                putInt("num5",newLotto.num5)
                putInt("num6",newLotto.num6)
                putInt("num7",newLotto.num7)
                putInt("pow1",newLotto.pow1)
                apply()
            }

            //Insert new Lotto Game into database
            db.lottoDao().insert(newLotto)

            //Update lotto list variable
            lottoList = db.lottoDao().getGamesSortedById()
        }

        //Used to clear all records saved in database
        btnReset.setOnClickListener{
            //Clear all database records
            db.lottoDao().deleteAll()

            //Reset TextViews to 0
            txtNum1.text = "0"
            txtNum2.text = "0"
            txtNum3.text = "0"
            txtNum4.text = "0"
            txtNum5.text = "0"
            txtNum6.text = "0"
            txtNum7.text = "0"
            txtNum8.text = "0"

            //Clear values from saved preferences when Reset button is clicked
            var newLotto = Lotto(0,0,0,0,0,0,
            0,0,0,0)
            //Use the values to be passed to SavedFragment
            val sharedPref = container?.context?.getSharedPreferences("SAVEDGAME",
                Context.MODE_PRIVATE) ?: return@setOnClickListener
            with(sharedPref.edit()){
                putInt("num1",newLotto.num1)
                putInt("num2",newLotto.num2)
                putInt("num3",newLotto.num3)
                putInt("num4",newLotto.num4)
                putInt("num5",newLotto.num5)
                putInt("num6",newLotto.num6)
                putInt("num7",newLotto.num7)
                putInt("pow1",newLotto.pow1)
                apply()
            }
        }
        return view
    }

    companion object {
         /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param newLotto Parameter 1.
         * @return A new instance of fragment RandomFragment.
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
        var currentLotto = Lotto(0,0,0,0,0,0,
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

    //Generate Random Numbers, num1-7 must be unique, no duplicates allowed and 1 Powerball value
    private fun getRandomLottoGame():Lotto{
        //Get 7 randomly generated numbers to be used that are unique and create Int Array
        var numberList = ThreadLocalRandom.current().ints(1,36)
            .distinct().limit(7).toArray()

        //Generate separate Random Number for Powerball
        var powerballNumber = Random.nextInt(1,21)

        val newLotto = Lotto(0,numberList[0],numberList[1],numberList[2],numberList[3],
            numberList[4],numberList[5],numberList[6],powerballNumber,(lottoList.size+1))

        Log.i("NUMBER_ID",newLotto.id.toString())

        //Return newly generated lotto numbers
        return newLotto
    }
}