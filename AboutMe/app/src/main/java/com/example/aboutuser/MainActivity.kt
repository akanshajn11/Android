package com.example.aboutuser

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import com.example.aboutuser.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = User()

        binding.submitButton.setOnClickListener {
            saveDetails(it)
            val toast: Toast = Toast.makeText(
                this,
                HtmlCompat.fromHtml("<b>Details saved</b>", HtmlCompat.FROM_HTML_MODE_LEGACY),
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            binding.Title.text = "Your details:"
        }

        spinnerAdapter(binding.userTypeEdit, "userType")
        spinnerAdapter(binding.genderEdit, "gender")
    }


    private fun saveDetails(it: View) {

        binding.apply {

            //bind the values of all the fields entered by user to the 'user' binding variable
            //spinner fields will bind in onItemSelected() function
            user?.name = nameEdit.text.toString()
            user?.nickname = nicknameEdit.text.toString()
            user?.email = emailEdit.text.toString()
            user?.tech = techEdit.text.toString()
            user?.city = cityEdit.text.toString()
            user?.projects = projectsEdit.text.toString()
            if (activeEdit.isChecked) {
                user?.active = "Yes"
            }
            invalidateAll()

            //remove the main scroll view: this would remove all the edit elements and the button within this view
            mainScrollView.visibility = View.GONE

            //add all the text layouts
            nameLayout.visibility = View.VISIBLE
            nicknameLayout.visibility = View.VISIBLE
            emailLayout.visibility = View.VISIBLE
            techLayout.visibility = View.VISIBLE
            cityLayout.visibility = View.VISIBLE
            genderLayout.visibility = View.VISIBLE
            typeLayout.visibility = View.VISIBLE
            activeLayout.visibility = View.VISIBLE

            projectsLabel.visibility = View.VISIBLE

            //add the projects scroll view
            projectsScrollView.visibility = View.VISIBLE

        }
        //Hide the keyboard on clicking submit button(it)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent == userType_edit)
            binding.user?.userType = parent?.getItemAtPosition(position).toString()
        if (parent == gender_edit)
            binding.user?.gender = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun spinnerAdapter(spinner: Spinner, fieldType: String) {

        spinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            this,
            when (fieldType) {
                "userType" -> R.array.userType
                else -> R.array.gender
            },
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }
    }
}
