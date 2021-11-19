package com.akansha.multiplefragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val bundle = bundleOf("int_value" to 0, "name" to "akansha")
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FragmentTwo>(R.id.fragment_container_view_2, "Fragment 2", args = bundle)
            }
        }
    }
}