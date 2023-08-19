package com.example.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
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
        val emptyButton: ImageButton = itemView.findViewById(R.id.emptyButton)
        val filledButton: ImageButton = itemView.findViewById(R.id.filledButton)

        // Set data to the TextViews
        departAirportView.text = selectedAirport
        arriveAirportView.text = "${airport.iata_code} ${airport.name}"

        emptyButton.setOnClickListener {
            // Handle the click event for the emptyButton within an item
            emptyButton.visibility = View.GONE
            filledButton.visibility = View.VISIBLE

            // Perform additional actions if needed
        }

        filledButton.setOnClickListener {
            // Handle the click event for the filledButton within an item
            filledButton.visibility = View.GONE
            emptyButton.visibility = View.VISIBLE

            // Perform additional actions if needed
        }


        return itemView
    }
}

//        val emptyButton: ImageView = itemView.findViewById(R.id.emptyButton)
//        emptyButton.visibility = View.VISIBLE
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