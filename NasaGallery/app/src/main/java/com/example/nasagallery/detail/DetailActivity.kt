package com.example.nasagallery.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.nasagallery.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.extras?.getString("title")
        val location = intent.extras?.getString("location")
        val photographer = intent.extras?.getString("photographer")
        val description = intent.extras?.getString("description")

        val titleView: TextView = findViewById(R.id.title)
        val locationView: TextView = findViewById(R.id.location)
        val photographerView: TextView = findViewById(R.id.photographer)
        val descriptionView: TextView = findViewById(R.id.description)
        val imageView: ImageView = findViewById(R.id.image)

        titleView.text = title
        locationView.text = location
        photographerView.text = photographer
        descriptionView.text = description

        Glide.with(this)
            .load(intent.extras?.getString("imageLink"))
            .into(imageView)
    }
}