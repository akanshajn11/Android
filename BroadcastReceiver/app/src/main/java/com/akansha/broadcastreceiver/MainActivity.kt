package com.akansha.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Register receiver
        registerReceiver(MyReceiver(), IntentFilter("com.akansha.broadcastreceiver.CUSTOM_INTENT"))

        var button = findViewById<Button>(R.id.btn_broadcast)

        button.setOnClickListener {
            var intent = Intent("com.akansha.broadcastreceiver.CUSTOM_INTENT")
            sendBroadcast(intent)
        }
    }
}