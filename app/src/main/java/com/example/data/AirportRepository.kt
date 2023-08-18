package com.example.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    /**
     * Retrieve all the airports from the the given data source.
     */
    fun getAllAirports(): Flow<List<Airport>>

    /**
     * Retrieve an airport from the given data source that matches with the [iata_code].
     */
    fun getAirportIata(iata: Int): Flow<Airport>

    /**
     * Retrieve an airport from the given data source that matches with the [name].
     */
    fun getAirportName(name: Int): Flow<Airport>
}
