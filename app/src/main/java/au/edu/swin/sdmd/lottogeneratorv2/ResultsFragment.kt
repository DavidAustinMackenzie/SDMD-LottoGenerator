package au.edu.swin.sdmd.lottogeneratorv2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.coroutineScope as coroutineScope

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var txtResult: TextView
    private var output=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        txtResult = view.findViewById<TextView>(R.id.txtResult)
        val btnResult = view.findViewById<Button>(R.id.btnResult)

        //Get Results
        btnResult?.setOnClickListener{
            GlobalScope.launch{
                    getResults()
            }
            txtResult.text = output
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private suspend fun getResults()
    {
        GlobalScope.launch {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://australia-lotto-live.p.rapidapi.com/get_latest_results/Powerball/1")
                .get()
                .addHeader("x-rapidapi-host", "australia-lotto-live.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "648430e8e0msh7a1e2690183b71ep15021djsnaf60e5fd8c14")
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body!!.string()
            output=responseBody
            Log.i("RESPONSE", output)
        }
    }
}