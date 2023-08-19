package com.example.ui

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.data.Airport
import com.example.data.R

class AirportListAdapter(private val context: Context,
                         private val resource: Int,
                         private val selectedAirport: String,
                         private val airportList: List<Airport>,
                         private val sharedPreferences: SharedPreferences
)
    : ArrayAdapter<Airport>(context, resource, airportList) {

    private val favoritedMap = HashMap<String, Boolean>() // Use a HashMap to track favoriting state

    init {
        // Load favorited states from SharedPreferences
        airportList.forEach { airport ->
            favoritedMap[airport.iata_code] = sharedPreferences.getBoolean(airport.iata_code, false)
        }
    }

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

        // Set button visibility based on favoriting state from favoritedMap
        if (favoritedMap[airport.iata_code] == true) {
            emptyButton.visibility = View.GONE
            filledButton.visibility = View.VISIBLE
        } else {
            filledButton.visibility = View.GONE
            emptyButton.visibility = View.VISIBLE
        }

        emptyButton.setOnClickListener {
            favoritedMap[airport.iata_code] = true
            saveFavoritedState(airport.iata_code, true)
            emptyButton.visibility = View.GONE
            filledButton.visibility = View.VISIBLE
        }

        filledButton.setOnClickListener {
            favoritedMap[airport.iata_code] = false
            saveFavoritedState(airport.iata_code, false)
            filledButton.visibility = View.GONE
            emptyButton.visibility = View.VISIBLE
        }

        return itemView
    }
    private fun saveFavoritedState(airportCode: String, isFavorited: Boolean) {
        favoritedMap[airportCode] = isFavorited
        sharedPreferences.edit().putBoolean(airportCode, isFavorited).apply()
    }
}
