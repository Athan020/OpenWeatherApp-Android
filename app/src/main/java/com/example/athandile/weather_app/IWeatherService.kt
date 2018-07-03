package com.example.athandile.weather_app

import com.example.athandile.weather_app.weather_data.Response

/**
 * Created by Athandile on 3/8/2018.
 */
interface IWeatherService {

    fun getWeather(City:String): Any
}