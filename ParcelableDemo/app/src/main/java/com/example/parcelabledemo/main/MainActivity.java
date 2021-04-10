package com.example.parcelabledemo.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcelabledemo.R;
import com.example.parcelabledemo.detail.DetailActivity;
import com.example.parcelabledemo.model.Details;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText tech;
    EditText location;
    Button button;
    Details details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit_name);
        tech = findViewById(R.id.edit_tech);
        location = findViewById(R.id.edit_location);
        button = findViewById(R.id.save_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_str = name.getText().toString();
                String tech_str = tech.getText().toString();
                String location_str = location.getText().toString();

                hideKeybaord(v);

                if (name_str.matches("") || tech_str.matches("") || location_str.matches("")) {
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                details = new Details(name_str, tech_str, location_str);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                ArrayList<Details> arrayList = new ArrayList<Details>();
                arrayList.add(details);
                intent.putParcelableArrayListExtra("Information", arrayList);
                startActivity(intent);
            }
        });
    }

    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }
}