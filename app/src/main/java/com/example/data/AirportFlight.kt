/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "iata_code") val iataCode: String,
    val passengers: Long
)

// Flight.kt
@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val sourceAirportIataCode: String,
    val destinationAirportIataCode: String
)

// FavoriteRoute.kt
@Entity(tableName = "favorite_routes")
data class FavoriteRoute(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val flightId: Int
)