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

    private val favoritedMap = HashMap<String, Boolean>() // Declare favoritedMap here

    // Define the generateKey function here
    private fun generateKey(departAirportCode: String, arriveAirportCode: String): String {
        return "$departAirportCode-$arriveAirportCode" // Concatenate depart and arrive codes
    }

    init {
        // Load favorited states from SharedPreferences
        airportList.forEach { airport ->
            val favoritedKey = generateKey(airport.iata_code, selectedAirport)
            favoritedMap[favoritedKey] = sharedPreferences.getBoolean(favoritedKey, false)
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

        val favoritedKey = generateKey(airport.iata_code, selectedAirport)

        // Set button visibility based on favoriting state from favoritedMap
        if (favoritedMap[favoritedKey] == true) {
            emptyButton.visibility = View.GONE
            filledButton.visibility = View.VISIBLE
        } else {
            filledButton.visibility = View.GONE
            emptyButton.visibility = View.VISIBLE
        }

        emptyButton.setOnClickListener {
            favoritedMap[favoritedKey] = true
            saveFavoritedState(favoritedKey, true)
            emptyButton.visibility = View.GONE
            filledButton.visibility = View.VISIBLE
        }

        filledButton.setOnClickListener {
            favoritedMap[favoritedKey] = false
            saveFavoritedState(favoritedKey, false)
            filledButton.visibility = View.GONE
            emptyButton.visibility = View.VISIBLE
        }

        return itemView
    }
    private fun saveFavoritedState(airportCode: String, isFavorited: Boolean) {
        favoritedMap[airportCode.toString()] = isFavorited
        sharedPreferences.edit().putBoolean(airportCode.toString(), isFavorited).apply()
    }
}
