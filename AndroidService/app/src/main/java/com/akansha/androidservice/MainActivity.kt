package com.akansha.androidservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var start: Button
    private lateinit var stop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var start = findViewById<Button>(R.id.button_start)
        val stop = findViewById<Button>(R.id.button_stop)

        start.setOnClickListener {
            startService(Intent(this, MusicService()::class.java))
        }
        stop.setOnClickListener {
            stopService(Intent(this, MusicService()::class.java))
        }
    }
}