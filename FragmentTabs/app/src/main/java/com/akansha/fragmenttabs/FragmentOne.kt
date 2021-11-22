package com.akansha.fragmenttabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            FragmentOne().apply {
                arguments = Bundle().apply {
                    putString("SECTION_NO", param1)
                }
            }
    }
}