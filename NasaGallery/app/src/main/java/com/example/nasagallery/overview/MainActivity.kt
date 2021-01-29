package com.example.nasagallery.overview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nasagallery.R
import com.example.nasagallery.databinding.ActivityMainBinding
import com.example.nasagallery.search.SearchActivity


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}