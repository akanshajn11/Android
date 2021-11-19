package com.akansha.multiplefragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class FragmentTwo : Fragment(R.layout.fragment_two) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get arguments
        val intValue = requireArguments().getInt("int_value")
        val stringValue = requireArguments().getString("name")
    }
}