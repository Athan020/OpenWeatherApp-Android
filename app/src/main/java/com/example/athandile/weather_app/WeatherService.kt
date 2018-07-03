package com.example.athandile.weather_app
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.widget.DecorContentParent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.athandile.weather_app.R.id.parent
import com.example.athandile.weather_app.R.id.weather_icon
import com.example.athandile.weather_app.WeatherItem.WeatherListItem
import com.example.athandile.weather_app.weather_data.Response
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_weather_details.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Athandile on 3/8/2018.
 */
public  class WeatherService(var context:Context): AsyncTask<String, Void, Response>()  {

//    interface AsyncResponse {
//        fun processFinish(output: Response)
//    }
//
//    var delegate: AsyncResponse? = null
//
//    fun WeatherService(delegate: AsyncResponse){
//        this.delegate = delegate
//    }
    private var statusProgress: ProgressBar?=null

//9cf52365eec1e401c168ae5dd2a42d0c = JP Key
    //460cc8bee30660deca0296e2f3ffef24
    private val baseUrl:String = "http://api.openweathermap.org/data/2.5/weather?q="
    private val Key:String = 

    lateinit var progressDialog: ProgressDialog;
    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Getting Data")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }
    private fun isConnectionAvailible():Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return  if(connectivityManager is ConnectivityManager){
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo.isConnected
        }else false
    }

    override fun doInBackground(vararg param: String?): Response {

        val c = java.net.URLEncoder.encode(param[0], "UTF-8")
        val query = baseUrl + c + Key

      var data: String? = null
        var Err:String? = null

       Log.d("Execute","This Actually ran")

        try {


                query.httpGet().responseString() { _, _, result ->

                    result.fold({ d ->

                        data = d

                    }, { err ->
                        Err = err.exception.message
                        Log.d("ERROR", err.exception.message)

                    })
                }


        Thread.sleep(2000)
        }catch (ex:Exception){

        Log.d("Error",ex.message.toString())
            ex.printStackTrace()
        }
        finally {

            return Gson().fromJson(data,Response::class.java)

        }

    }

    private  class ViewHolder(detalscreen: View?){
        var weather_icon:ImageView
        var temp:TextView
        var weatherLocation:TextView
        var weatherList:ListView
        init {
            this.weather_icon = detalscreen?.findViewById(R.id.weather_icon) as ImageView
            this.temp = detalscreen?.findViewById(R.id.temp) as TextView
            this.weatherLocation =   detalscreen?.findViewById(R.id.weather_location) as TextView
            this.weatherList = detalscreen?.findViewById(R.id.listView)
        }

    }

  override fun onPostExecute(result: Response?){
      //  super.onPostExecute(result)
      var layout = LayoutInflater.from(context)
        Log.d("Result",result.toString())
      var viewHolder:ViewHolder = ViewHolder(layout.inflate(R.layout.activity_weather_details,null,false))
      val imgUrl:String = "http://openweathermap.org/img/w/"+result?.weather?.get(0)?.icon.toString()+".png"
      Picasso.get().load(imgUrl).resize(250,250).into(viewHolder.weather_icon)

//      var listview = findViewById<ListView>(R.id.listView)
      var ItemArray:ArrayList<WeatherListItem> = ArrayList()

//      temp.setText(output.main?.temp?.toInt().toString()+" C")
//
//      weather_location.append(" "+ result?.name + "," + result?.sys?.country)
      var windspeed:Double = result?.wind?.speed?.toDouble() as Double

      var windRating:String = " "
      if(windspeed < 1){
          windRating = "Calm";
      }else  if(windspeed>=1 && windspeed<6){
          windRating = "Light Air";
      }else  if(windspeed>=6 && windspeed<12){
          windRating = "Light Breeze";
      }else  if(windspeed>=12 && windspeed<20){
          windRating = "Gentle Breeze";
      }else  if(windspeed>=20 && windspeed<29){
          windRating = "Moderate Breeze";
      }else  if(windspeed>=29 && windspeed<39){
          windRating = "Fresh Breeze";
      }else  if(windspeed>=39 && windspeed<50){
          windRating = "Strong Breeze";
      }else  if(windspeed>=50 && windspeed<62){
          windRating = "High Wind";
      }else  if(windspeed>=62 && windspeed<75){
          windRating = "Gale";
      }else  if(windspeed>=75 && windspeed<89){
          windRating = "Severe Gale";
      }else  if(windspeed>=89 && windspeed<103){
          windRating = "Storm";
      }else  if(windspeed>=103 && windspeed<118){
          windRating = "Violent Storm";
      }else  if(windspeed>=118){
          windRating = "Hurricane Force";
      }

      var windDeg:Int =result?.wind?.deg as Int

      var windDirection:String = " "

      if(windDeg == 0 || windDeg == 360){
          windDirection = "North";
      }else if(windDeg>0 && windDeg < 45){
          windDirection = "North North-East";
      }else if(windDeg == 45){
          windDirection = "North-East";
      }else if(windDeg>45 && windDeg < 90){
          windDirection = "East North-East";
      }else if(windDeg == 90){
          windDirection = "East";
      }else if(windDeg>90 && windDeg < 135){
          windDirection = "East South-East";
      }else if(windDeg == 135){
          windDirection = "South-East";
      }else if(windDeg>135 && windDeg < 180){
          windDirection = "South South-East";
      }else if(windDeg == 180){
          windDirection = "South";
      }else if(windDeg>180 && windDeg < 225){
          windDirection = "South South-West";
      }else if(windDeg == 225){
          windDirection = "South-West";
      }else if(windDeg>225 && windDeg < 270){
          windDirection = "West South-West";
      }else if(windDeg == 270){
          windDirection = "West";
      }else if(windDeg>270 && windDeg < 315){
          windDirection = "West North-West";
      }else if(windDeg == 315){
          windDirection = "North-West";
      }else if(windDeg>315 && windDeg < 360){
          windDirection = "West North-West";
      }
      ItemArray.add(WeatherListItem("Wind",windRating + " "+ windspeed+" m/s \n "+ windDirection +" "+windDeg +" deg"  ))
      ItemArray.add(WeatherListItem("Cloudiness",result?.weather?.get(0)?.description.toString()))
      ItemArray.add(WeatherListItem("Pressure",result?.main?.pressure.toString()+ " hpa"))
      ItemArray.add(WeatherListItem("Humidity",result?.main?.humidity.toString() +" %"))
      ItemArray.add(WeatherListItem("Sunrise",epochToNormalTime(result?.sys?.sunrise.toString())))
      ItemArray.add(WeatherListItem("Sunset",epochToNormalTime(result?.sys?.sunset.toString())))
      ItemArray.add(WeatherListItem("Geo Coords","["+ result?.coord?.lat.toString()+","+result?.coord?.lon.toString()+"]"))

//        ItemArray.add(WeatherListItem("Cloudiness","very cloudy"))
//        ItemArray.add(WeatherListItem("Pressure","too much"))
//        ItemArray.add(WeatherListItem("Humidity","Like Alot"))
//        ItemArray.add(WeatherListItem("Sunrise","Early"))
//        ItemArray.add(WeatherListItem("Sunset","Quite late "))
//        ItemArray.add(WeatherListItem("Geo Coords","right here"))
      viewHolder.weatherList.adapter = WeatherDetailsAdapter(context, ItemArray)
//       if (result?.weather != null) {
//
//           delegate?.processFinish(result)
//       }


    progressDialog.dismiss()


    }
    fun epochToNormalTime(value: String):String{

        var date = Date(value.toLong() * 1000L)
        var ft = SimpleDateFormat("HH:mm")
        //  format.timeZone = TimeZone.getTimeZone("UTC")

        return ft.format(date)
    }


//    class Deserializer<T : Any> (val javaclassname: Class<T>) : ResponseDeserializable<T> {
//        override fun deserialize(content: String) = Gson().fromJson(content, javaclassname)
//    }

}
