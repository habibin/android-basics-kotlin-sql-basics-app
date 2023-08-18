package com.example.data

import kotlinx.coroutines.flow.Flow

class AirportRepository(private val airportDao: AirportDao) {
    fun searchAirports(searchQuery: String): Flow<List<Airport>> {
        return airportDao.searchAirports(searchQuery)
    }

    fun getAllExcept(searchQuery: String): Flow<List<Airport>> {
        return airportDao.getAllExcept(searchQuery)
    }

}
