// DestinationActivity.kt
package com.example.travel_app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DestinationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)

        val titleText: TextView = findViewById(R.id.titleText)
        val descriptionText: TextView = findViewById(R.id.descriptionText)
        val cityImageView: ImageView = findViewById(R.id.cityImageView)
        val backButton: Button = findViewById(R.id.backButton)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        titleText.text = title
        descriptionText.text = description

        // Set the appropriate image based on the title
        val imageResource = when (title) {
            "Greenwich Boat Ride" -> R.drawable.greenwich_cutty_sark
            "Westminster to Kew Pier" -> R.drawable.westminster_kew_pier
            "Thames River Sightseeing" -> R.drawable.thames_river_sightseeing
            "Brighton" -> R.drawable.brighton
            "Bristol" -> R.drawable.bristol
            "Birmingham" -> R.drawable.birmingham
            "Paris" -> R.drawable.paris
            "Amsterdam" -> R.drawable.amsterdam
            "Brussels" -> R.drawable.brussels
            else -> R.drawable.default_image // Optional default image
        }

        cityImageView.setImageResource(imageResource)

        backButton.setOnClickListener {
            finish()
        }
    }
}
