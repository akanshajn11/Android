package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Binding Object

    private val myName : MyName = MyName("Akansha Jain")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.doneButton.setOnClickListener{
            addNickName(it)
        }
        binding.myName= myName


//        findViewById<Button>(R.id.done_button).setOnClickListener{
//           addNickName(it)
//        }
    }

    private fun addNickName(it: View) {

        binding.apply {
//            val nicknameEdit: TextView = findViewById(R.id.nickname_edit)
//            val nicknameText: TextView = findViewById(R.id.nickname_text)
//            nicknameText.text = nicknameEdit.text
//            nicknameText.visibility = View.VISIBLE
//            nicknameEdit.visibility = View.GONE
//            it.visibility = View.GONE


            myName?.nickname=nicknameEdit.text.toString()

            //nicknameText.text=nicknameEdit.text
            invalidateAll()
            nicknameText.visibility=View.VISIBLE
            nicknameEdit.visibility = View.GONE
            it.visibility=View.GONE


        }
        //Hide the keyboard

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)

    }
}
