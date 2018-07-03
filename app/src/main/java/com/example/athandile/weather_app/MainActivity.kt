package com.example.athandile.weather_app

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(View.OnClickListener { getCityName() })
    }

    fun getCityName() {

            val city= findViewById(R.id.city) as EditText
            val Query:String = city.text.toString()
            Toast.makeText(this,Query,Toast.LENGTH_LONG)

            //weatherService.collectData(Query)
            Log.d("Query",Query.toString())
            val Intent = Intent(this,WeatherDetailsActivity::class.java)
    if(!isConnectionAvailible()){
        Toast.makeText(this,"No internet Connection", Toast.LENGTH_LONG).show()
    }else{
        Intent.putExtra("City",Query)
        startActivity(Intent)
    }

//        Toast.makeText(this,"Internet Connection Not Found",Toast.LENGTH_SHORT)

////
//        if(!isConnectionAvailible()){
//
//        }else{
//            Toast.makeText(this,"Network Found",Toast.LENGTH_LONG)
//        }


    }

    private fun isConnectionAvailible():Boolean{
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return  if(connectivityManager is ConnectivityManager){
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo.isConnected
        }else false
    }

}
