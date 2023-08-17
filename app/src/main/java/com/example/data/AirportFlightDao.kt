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

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AirportDao {
    @Insert
    suspend fun insertAll(airports: List<Airport>)
    @Query("SELECT * FROM airport")
    suspend fun getAll(): List<Airport>

    @Query("SELECT * FROM airport WHERE name LIKE :searchQuery OR iata_code LIKE :searchQuery")
    fun searchAirports(searchQuery: String): List<Airport>
}
