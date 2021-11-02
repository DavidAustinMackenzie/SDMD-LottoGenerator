package au.edu.swin.sdmd.lottogeneratorv2.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import au.edu.swin.sdmd.lottogeneratorv2.R
import au.edu.swin.sdmd.lottogeneratorv2.db.LottoRoomDatabase
import au.edu.swin.sdmd.lottogeneratorv2.models.Lotto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class UpdateActivityDialogFragment : BottomSheetDialogFragment() {

    //Curent Lotto Game Reference
     private var currentLottoGame: Lotto? = null

    //Get output elements
    lateinit var txtUpdateHeading: TextView
    lateinit var txtNum1: TextView
    lateinit var txtNum2: TextView
    lateinit var txtNum3: TextView
    lateinit var txtNum4: TextView
    lateinit var txtNum5: TextView
    lateinit var txtNum6: TextView
    lateinit var txtNum7: TextView
    lateinit var txtNum8: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.dialog_fragment_update, container, false)
        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)

        //Create Database Reference
        val db = context?.let { LottoRoomDatabase(it) }

        //Get the value passed as an argument to this Fragment
        val id = this.arguments?.getInt("LOTTO_ID")
        //Get the current game from the database
        var lottoGame = db?.lottoDao()?.getGame(id!!)

        //Get all the variables from the lotto Game Selected
        lottoGame?.let {
            updateValues(it,view)
        }

        //Update the current game that is selected
        btnUpdate.setOnClickListener{
            currentLottoGame = getNewRandomLottoGame(lottoGame!!)
            updateValues(currentLottoGame!!,view)
        }

        return view
    }

    override fun onStop(){
        if(currentLottoGame != null){
            //Create Database Reference
            val db = context?.let { LottoRoomDatabase(it) }
            db?.lottoDao()?.update(currentLottoGame!!)
            Toast.makeText(context,"Game updated!",Toast.LENGTH_SHORT).show()
        }
        super.onStop()
    }

    //Generate Random Numbers, num1-7 must be unique, no duplicates allowed and 1 Powerball value
    private fun getNewRandomLottoGame(lottoGame: Lotto): Lotto {
        //Get 7 randomly generated numbers to be used that are unique and create Int Array
        var numberList = ThreadLocalRandom.current().ints(1,36)
            .distinct().limit(7).toArray()

        //Generate separate Random Number for Powerball
        var powerballNumber = Random.nextInt(1,21)

        val newLottoGame = Lotto(lottoGame.id,numberList[0],numberList[1],numberList[2],numberList[3],
            numberList[4],numberList[5],numberList[6],powerballNumber,lottoGame.game)

        //Return newly generated lotto numbers
        return newLottoGame
    }

    //Update values on the view
    private fun updateValues(currentLotto: Lotto, view: View)
    {
        //Update the values on the BottomDialogFragment
        txtUpdateHeading = view.findViewById(R.id.txtUpdateHeading)
        txtUpdateHeading.text = "Lotto Game: ${currentLotto.game}"
        txtNum1 = view.findViewById(R.id.txtUpdateNum1)
        txtNum1.text = currentLotto.num1.toString()
        txtNum2 = view.findViewById(R.id.txtUpdateNum2)
        txtNum2.text = currentLotto.num2.toString()
        txtNum3 = view.findViewById(R.id.txtUpdateNum3)
        txtNum3.text = currentLotto.num3.toString()
        txtNum4 = view.findViewById(R.id.txtUpdateNum4)
        txtNum4.text = currentLotto.num4.toString()
        txtNum5 = view.findViewById(R.id.txtUpdateNum5)
        txtNum5.text = currentLotto.num5.toString()
        txtNum6 = view.findViewById(R.id.txtUpdateNum6)
        txtNum6.text = currentLotto.num6.toString()
        txtNum7 = view.findViewById(R.id.txtUpdateNum7)
        txtNum7.text = currentLotto.num7.toString()
        txtNum8 = view.findViewById(R.id.txtUpdateNum8)
        txtNum8.text = currentLotto.pow1.toString()
    }
}