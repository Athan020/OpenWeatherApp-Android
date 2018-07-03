package com.example.athandile.weather_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.widget.ListView
import android.widget.Toast
import com.example.athandile.weather_app.WeatherItem.WeatherListItem
import com.example.athandile.weather_app.weather_data.Response
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_weather_details.*
import kotlinx.android.synthetic.main.activity_weather_details.view.*
import org.jetbrains.anko.doAsync
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class WeatherDetailsActivity : AppCompatActivity() {


    // val weatherService :WeatherService= WeatherService()
    val asyncTask: WeatherService = WeatherService(this)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)

        val date =  Date()
        val ft = SimpleDateFormat("yyyy-MM-dd")
        time_stamp.setText("got at "+ ft.format(date))
        val city:String = intent.getStringExtra("City")
        asyncTask.execute(city)

    }


    override fun onStart() {
        super.onStart()

    }

//    override fun processFinish(output: Response) {
//
//        val imgUrl:String = "http://openweathermap.org/img/w/"+output.weather?.get(0)?.icon.toString()+".png"
//        Picasso.get().load(imgUrl).resize(250,250).into(weather_icon)
//
//        var listview = findViewById<ListView>(R.id.listView)
//        var ItemArray:ArrayList<WeatherListItem> = ArrayList()
//
//        temp.setText(output.main?.temp?.toInt().toString()+" C")
//
//        weather_location.append(" "+ output.name + "," + output.sys?.country)
//       var windspeed:Double = output.wind?.speed?.toInt() as Double
//
//        var windRating:String = " "
//        if(windspeed < 1){
//            windRating = "Calm";
//        }else  if(windspeed>=1 && windspeed<6){
//            windRating = "Light Air";
//        }else  if(windspeed>=6 && windspeed<12){
//            windRating = "Light Breeze";
//        }else  if(windspeed>=12 && windspeed<20){
//            windRating = "Gentle Breeze";
//        }else  if(windspeed>=20 && windspeed<29){
//            windRating = "Moderate Breeze";
//        }else  if(windspeed>=29 && windspeed<39){
//            windRating = "Fresh Breeze";
//        }else  if(windspeed>=39 && windspeed<50){
//            windRating = "Strong Breeze";
//        }else  if(windspeed>=50 && windspeed<62){
//            windRating = "High Wind";
//        }else  if(windspeed>=62 && windspeed<75){
//            windRating = "Gale";
//        }else  if(windspeed>=75 && windspeed<89){
//            windRating = "Severe Gale";
//        }else  if(windspeed>=89 && windspeed<103){
//            windRating = "Storm";
//        }else  if(windspeed>=103 && windspeed<118){
//            windRating = "Violent Storm";
//        }else  if(windspeed>=118){
//            windRating = "Hurricane Force";
//        }
//
//        var windDeg:Int =output.wind?.deg as Int
//
//         var windDirection:String = " "
//
//        if(windDeg == 0 || windDeg == 360){
//            windDirection = "North";
//        }else if(windDeg>0 && windDeg < 45){
//            windDirection = "North North-East";
//        }else if(windDeg == 45){
//            windDirection = "North-East";
//        }else if(windDeg>45 && windDeg < 90){
//            windDirection = "East North-East";
//        }else if(windDeg == 90){
//            windDirection = "East";
//        }else if(windDeg>90 && windDeg < 135){
//            windDirection = "East South-East";
//        }else if(windDeg == 135){
//            windDirection = "South-East";
//        }else if(windDeg>135 && windDeg < 180){
//            windDirection = "South South-East";
//        }else if(windDeg == 180){
//            windDirection = "South";
//        }else if(windDeg>180 && windDeg < 225){
//            windDirection = "South South-West";
//        }else if(windDeg == 225){
//            windDirection = "South-West";
//        }else if(windDeg>225 && windDeg < 270){
//            windDirection = "West South-West";
//        }else if(windDeg == 270){
//            windDirection = "West";
//        }else if(windDeg>270 && windDeg < 315){
//            windDirection = "West North-West";
//        }else if(windDeg == 315){
//            windDirection = "North-West";
//        }else if(windDeg>315 && windDeg < 360){
//            windDirection = "West North-West";
//        }
//      ItemArray.add(WeatherListItem("Wind",windRating + " "+ windspeed+" m/s \n "+ windDirection +" "+windDeg +" deg"  ))
//        ItemArray.add(WeatherListItem("Cloudiness",output.weather?.get(0)?.description.toString()))
//        ItemArray.add(WeatherListItem("Pressure",output.main?.pressure.toString()+ " hpa"))
//        ItemArray.add(WeatherListItem("Humidity",output.main?.humidity.toString() +" %"))
//        ItemArray.add(WeatherListItem("Sunrise",epochToNormalTime(output.sys?.sunrise.toString())))
//        ItemArray.add(WeatherListItem("Sunset",epochToNormalTime(output.sys?.sunset.toString())))
//        ItemArray.add(WeatherListItem("Geo Coords","["+ output.coord?.lat.toString()+","+output.coord?.lon.toString()+"]"))
//
////        ItemArray.add(WeatherListItem("Cloudiness","very cloudy"))
////        ItemArray.add(WeatherListItem("Pressure","too much"))
////        ItemArray.add(WeatherListItem("Humidity","Like Alot"))
////        ItemArray.add(WeatherListItem("Sunrise","Early"))
////        ItemArray.add(WeatherListItem("Sunset","Quite late "))
////        ItemArray.add(WeatherListItem("Geo Coords","right here"))
//        listview.adapter = WeatherDetailsAdapter(applicationContext, ItemArray)
//
////        co_ords?.setText("["+ output.coord?.lat.toString()+","+output.coord?.lon.toString()+"]")
////       // weather_icon.setImageResource(output.weather?.get(0)?.icon.toString().toInt())
//
////        temp.setText(output.main?.temp.toString().substring(0,2)+" C")
////        forecast.setText()
////        sunrise.setText(epochToNormalTime(output.sys?.sunrise.toString()))
////        sunset.setText((epochToNormalTime(output.sys?.sunset.toString())))
////        humidity.setText(output.main?.humidity.toString() +" %")
////        pressure.setText(output.main?.pressure.toString()+ " hpa")
//    }


    fun epochToNormalTime(value: String):String{

        var date = Date(value.toLong() * 1000L)
        var ft = SimpleDateFormat("HH:mm")
      //  format.timeZone = TimeZone.getTimeZone("UTC")

        return ft.format(date)
    }
}
