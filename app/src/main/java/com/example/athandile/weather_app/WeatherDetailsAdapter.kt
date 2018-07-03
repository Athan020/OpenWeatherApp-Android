package com.example.athandile.weather_app

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.athandile.weather_app.WeatherItem.WeatherListItem

class WeatherDetailsAdapter(var context: Context, var WeatherItem:ArrayList<WeatherListItem>) : BaseAdapter() {

    private class ViewHolder(row:View?){
        var txtLabel: TextView
        var txtData:TextView

        init {
            this.txtLabel = row?.findViewById(R.id.tbName) as TextView
            this.txtData = row?.findViewById(R.id.tbData) as TextView
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout= LayoutInflater.from(context)
            view = layout.inflate(R.layout.details_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var WeatherItem:WeatherListItem = getItem(position) as WeatherListItem
        viewHolder.txtLabel.text = WeatherItem.name
        viewHolder.txtData.text = WeatherItem.data

        if((position)% 2 == 0){
            viewHolder.txtLabel.setBackgroundResource(R.drawable.border1)
            viewHolder.txtData.setBackgroundResource(R.drawable.border1)
        }
        if((position) == (count - 1) ){
            viewHolder.txtData.setTextColor(Color.rgb(255,127,80))
        }

        return view as View
    }

    override fun getItem(position: Int): Any {
    return  WeatherItem.get(position)
    }

    override fun getItemId(position: Int): Long {
    return  position.toLong()
    }

    override fun getCount(): Int {
    return WeatherItem.count()
    }


}