package com.example.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.data.Airport
import com.example.data.R

class AirportListAdapter(context: Context, private val resource: Int, private val selectedAirport: String, private val airportList: List<Airport>)
    : ArrayAdapter<Airport>(context, resource, airportList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val airport = airportList[position]

        val departAirportView: TextView = itemView.findViewById(R.id.departAirportView)
        val arriveAirportView: TextView = itemView.findViewById(R.id.arriveAirportView)

        // Set data to the TextViews
        departAirportView.text = selectedAirport
        arriveAirportView.text = "${airport.iata_code} ${airport.name}"

        val emptyButton: ImageView = itemView.findViewById(R.id.emptyButton)
        emptyButton.visibility = View.VISIBLE

//        // Check if the current item is a favorite
//        val isFavorite = favoriteItems.contains(position)
//
//        // Update the visibility of the favorite icons based on the favorite state
//        if (isFavorite) {
//            filledFavoriteIcon.visibility = View.VISIBLE
//            emptyFavoriteIcon.visibility = View.GONE
//        } else {
//            filledFavoriteIcon.visibility = View.GONE
//            emptyFavoriteIcon.visibility = View.VISIBLE
//        }

        return itemView
    }
}