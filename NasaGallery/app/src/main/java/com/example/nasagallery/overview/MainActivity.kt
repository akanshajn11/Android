package com.example.nasagallery.overview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nasagallery.R
import com.example.nasagallery.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OverviewAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel =
            ViewModelProvider(this, NasaViewModelFactory()).get(NasaViewModel::class.java)

        binding.lifecycleOwner = this

        val searchValue = intent.extras?.getString("searchValue")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)

        fetchImages(viewModel, searchValue)

        swipeRefreshLayout.setOnRefreshListener {
            fetchImages(viewModel, searchValue)
            Toast.makeText(applicationContext, "Images Refreshed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchImages(viewModel: NasaViewModel, searchValue: String?) {
        swipeRefreshLayout.isRefreshing = true

        if (searchValue != null)
            viewModel.getData(searchValue)

        viewModel.items.observe(this, Observer { itemsList ->
            adapter = OverviewAdapter(itemsList.map { it.links })
            recyclerView.adapter = adapter
            swipeRefreshLayout.isRefreshing = false
        })
    }
}