package com.example.nasagallery.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.example.nasagallery.R
import com.example.nasagallery.overview.MainActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var activityIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = findViewById(R.id.search_box)

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName)) //enable search on SearchView
            setIconifiedByDefault(false) // show search box also with search icon
        }
        activityIntent = Intent(this, MainActivity::class.java)

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY).also { query ->
                activityIntent.putExtra("searchValue", query)
                startActivity(activityIntent)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {

        if (Intent.ACTION_SEARCH == intent?.action) {
            intent.getStringExtra(SearchManager.QUERY).also { query ->
                activityIntent.putExtra("searchValue", query)
                startActivity(activityIntent)
            }
        }
        super.onNewIntent(intent)
    }
}