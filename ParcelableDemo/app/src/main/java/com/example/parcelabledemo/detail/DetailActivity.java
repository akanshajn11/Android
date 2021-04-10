package com.example.parcelabledemo.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parcelabledemo.R;
import com.example.parcelabledemo.model.Details;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView text_name, text_tech, text_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        text_name = findViewById(R.id.name);
        text_tech = findViewById(R.id.tech);
        text_location = findViewById(R.id.location);

        Intent intent = getIntent();
        List<Details> user = intent.getParcelableArrayListExtra("Information");

        text_name.setText("Name: " + user.get(0).getName());
        text_tech.setText("Technology: " + user.get(0).getTech());
        text_location.setText("Location: " + user.get(0).getLocation());

    }
}