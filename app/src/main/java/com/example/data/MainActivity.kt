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
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eventHandler()
    }


    private fun eventHandler() {
        // Retrieve the EditText
        val searchBox = findViewById<EditText>(R.id.searchEditText)

        // Set a TextWatcher
        searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is changing
                val searchText = s.toString()
                Log.i("MainActivity", "Search Text: $searchText")
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text changes
            }
        })

        // Find and set a click listener for the search button
        val searchButton = findViewById<FloatingActionButton>(R.id.search_button)
        searchButton.setOnClickListener {
            val searchText = searchBox.text.toString()
            Log.i("MainActivity", "Search Text when button clicked: $searchText")
            Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
        }
    }
}


