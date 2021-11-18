package com.akansha.screenorientation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var textView: EditText
    private lateinit var text: String
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var incButton = findViewById<Button>(R.id.btn_inc)
        var showButton = findViewById<Button>(R.id.btn_show)
        textView = findViewById(R.id.editText)
        text = textView.text.toString()

        incButton.setOnClickListener {
            counter++
        }

        showButton.setOnClickListener {
            Toast.makeText(
                this, counter.toString(), Toast.LENGTH_SHORT
            ).show()
        }
    }

    // called after onStart() when the activity is recreated
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        textView.setText(savedInstanceState.getString("editText"))
        counter = savedInstanceState.getInt("counter")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        text = textView.text.toString()
        outState.putString("editText", text)
        outState.putInt("counter", counter)
    }
}