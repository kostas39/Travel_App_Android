// MainActivity.kt
package com.example.travel_app

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var destinationSpinner: Spinner
    private lateinit var submitButton: Button
    private lateinit var clearButton: Button

    private val destinations = mapOf(
        "Boat" to listOf(
            "Greenwich Boat Ride",
            "Westminster to Kew Pier",
            "Thames River Sightseeing"
        ),
        "Train" to listOf(
            "Brighton",
            "Bristol",
            "Birmingham"
        ),
        "Airplane" to listOf(
            "Paris",
            "Amsterdam",
            "Brussels"
        )
    )

    private val destinationDetails = mapOf(
        "Greenwich Boat Ride" to "The Greenwich Boat Ride takes approximately 1 hour, offering a scenic cruise along the Thames River, passing iconic landmarks such as the Tower Bridge and the Cutty Sark before arriving at the historic Royal Borough of Greenwich.",
        "Westminster to Kew Pier" to "This relaxing journey from Westminster Pier to Kew Pier lasts around 1 hour and 30 minutes, taking passengers through the heart of London, passing landmarks like the Houses of Parliament, and ending near the world-renowned Kew Gardens.",
        "Thames River Sightseeing" to "The Thames River Sightseeing tour typically lasts about 30–60 minutes, offering tourists a narrated cruise along Thames river, showcasing sights like St. Paul’s Cathedral, the London Eye, and Shakespeare Globe Theatre, with hop-on-hop-off options at various piers.",
        "Brighton" to "Located about 1 hour and 30 minutes by car from London, Brighton is a vibrant seaside city known for its iconic pier, colorful beach huts, and the historic Royal Pavilion. It’s a popular destination for its eclectic mix of shops, lively arts scene, and pebble beach.",
        "Bristol" to "A 2-hour drive from London, Bristol is a dynamic city famous for its maritime history, the Clifton Suspension Bridge, and vibrant street art, including works by Banksy. Visitors can explore the Harbourside, cultural venues, and its rich industrial heritage.",
        "Birmingham" to "Birmingham is roughly 2 hours and 30 minutes by car from London and is celebrated as a cultural and economic hub in the Midlands. Known for its canals, shopping at the Bullring, and attractions like the Cadbury World, it offers a mix of industrial heritage and modern development.",
        "Paris" to "A flight from London to Paris takes around 1 hour and 15 minutes, bringing visitors to one of the world’s most iconic cities. Famous for landmarks like the Eiffel Tower, the Louvre, and Notre-Dame Cathedral, Paris is renowned for its art, fashion, and rich history.",
        "Amsterdam" to "A quick 1-hour flight from London takes you to Amsterdam, a city known for its picturesque canals, vibrant art scene, and historical sites like the Anne Frank House. The city is also famous for its cycling culture and lively coffee shops.",
        "Brussels" to "A 1-hour and 10-minute flight from London, Brussels offers a blend of modern and medieval charm. Known for its stunning Grand Place, chocolates, waffles, and EU headquarters, the city is a hub for both politics and culture in Europe."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup)
        destinationSpinner = findViewById(R.id.destinationSpinner)
        submitButton = findViewById(R.id.submitButton)
        clearButton = findViewById(R.id.clearButton)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedMode = when (checkedId) {
                R.id.boatButton -> "Boat"
                R.id.trainButton -> "Train"
                R.id.airplaneButton -> "Airplane"
                else -> ""
            }
            updateSpinnerOptions(selectedMode)
        }

        submitButton.setOnClickListener {
            val selectedDestination = destinationSpinner.selectedItem as? String
            val description = destinationDetails[selectedDestination]
            if (selectedDestination != null && description != null) {
                val intent = Intent(this, DestinationActivity::class.java)
                intent.putExtra("title", selectedDestination)
                intent.putExtra("description", description)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please make a selection!", Toast.LENGTH_SHORT).show()
            }
        }

        clearButton.setOnClickListener {
            radioGroup.clearCheck()
            destinationSpinner.adapter = null
        }
    }

    private fun updateSpinnerOptions(mode: String) {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            destinations[mode] ?: emptyList()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        destinationSpinner.adapter = adapter
    }
}
