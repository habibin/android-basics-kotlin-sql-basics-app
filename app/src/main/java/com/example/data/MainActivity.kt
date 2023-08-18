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

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var airportRepository: AirportRepository

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appDatabase =
            AppDatabase.getDatabase(applicationContext) // Initialize your Room database
        val airportDao = appDatabase.AirportDao() // Access the DAO
        airportRepository = AirportRepository(airportDao)

        eventHandler()
    }

    private fun eventHandler() {
        // Retrieve the AutoCompleteTextView
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.searchEditText)

        // Set up the adapter for the AutoCompleteTextView
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line)
        autoCompleteTextView.setAdapter(adapter)

        // Set a TextWatcher to update suggestions as the user types
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is changing
                val searchText = s.toString()

                lifecycleScope.launch {
                    // Call the searchAirports function from the repository
                    val airportsFlow = airportRepository.searchAirports(searchText)

                    // Observe the Flow using a coroutine
                    airportsFlow.collect { airports ->
                        Log.i("MainActivity", "airports: $airports")
                        // Update the adapter with the new suggestions
                        adapter.clear()
                        adapter.addAll(airports.map { airport -> airport.name })
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text changes
            }
        })
    }
}


