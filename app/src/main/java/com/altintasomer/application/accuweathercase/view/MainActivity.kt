package com.altintasomer.application.accuweathercase.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.altintasomer.application.accuweathercase.R
import com.altintasomer.application.accuweathercase.viewmodel.GeneralViewModelModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    private val viewModel : GeneralViewModelModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController

        init()

        handleIntent(intent)

    }
    private fun init() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
        }
    }

    /**
     * Burada arama menu sunu oluşturduk
     * */

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

  override fun onNewIntent(intent: Intent) {
      super.onNewIntent(intent)
      handleIntent(intent)
  }

    /**
     * Burada arama action ını handle edip son 5 aramaya kadar kaydettik.
     * son 5 aramamız arama alanına tıkladığımız öneri olarak çıkacak
     * */
    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                SearchRecentSuggestionsLimited(
                    this,
                    MySearchQueryContentProvider.AUTHORITY,
                    MySearchQueryContentProvider.MODE
                )
                    .saveRecentQuery(query, null)
                Log.d(TAG, "handleIntent: "+query)
                viewModel.setSearchQuery(query)
                val action = R.id.action_mainFragment_to_listFragment
                navController.navigate(action)
            }
        }
    }
}