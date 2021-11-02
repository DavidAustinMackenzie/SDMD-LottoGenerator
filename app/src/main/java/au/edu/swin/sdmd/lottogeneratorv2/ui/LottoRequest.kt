package au.edu.swin.sdmd.lottogeneratorv2.ui

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

object LottoRequest {
        suspend fun getResults()
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
                Log.i("RESPONSE", responseBody)
            }
        }
    }