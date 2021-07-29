package com.example.filmscatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun run() {
        url = "https://api.themoviedb.org/3/discover/movie?api_key=6ccd72a2a8fc239b13f209408fc31c33&language=ru-RU&sort_by=popularity.desc&page=$page&with_watch_monetization_types=flatrate"
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("dev", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val strResponse = response.body!!.string()
                //creating json object
                val jsonContact = JSONObject(strResponse)
                //creating json array
                val JSONarrInfo:JSONArray= jsonContact.getJSONArray("result")
                val size:Int = JSONarrInfo.length()

                for (i in 0 until size) {
                    val jsonObjectdetail:JSONObject=JSONarrInfo.getJSONObject(i)
                    post[i].id=jsonObjectdetail.getInt("id")
                    post[i].description=jsonObjectdetail.getString("description")
                    post[i].gifURL=jsonObjectdetail.getString("gifURL")
                }

                Log.i("ss", post[0].description)
                Log.i("ss", post[1].description)
                Log.i("ss", post[2].description)
                Log.i("ss", post[3].description)
                Log.i("ss", post[4].description)
            }
        })
    }
}