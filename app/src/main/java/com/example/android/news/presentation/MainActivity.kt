package com.example.android.news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.news.R
import com.example.android.news.data.repo.RepositoryImp
import com.example.android.news.databinding.ActivityMainBinding
import com.example.android.news.domain.usrcase.NewsUseCase
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var menuActivity: Menu

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.fgm_navHost)
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        menuActivity = menu!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        bottomNavigationView.isVisible = false
        menuActivity[0].isVisible = false
        return NavigationUI.onNavDestinationSelected(item, findNavController(R.id.fgm_navHost))
    }

    override fun onSupportNavigateUp(): Boolean
    {
        bottomNavigationView.isVisible = true
        return findNavController(R.id.fgm_navHost).navigateUp()
    }
}


// api key: f784d9b0fd504feb820b7d95221df3ff

/** get news for country **/
//https://newsapi.org/v2/everything?
// country=eg&
// category=sports&          business - sports - science - technology
// language=ar&     ar - en
// apiKey=f784d9b0fd504feb820b7d95221df3ff

/** search for news **/
//https://newsapi.org/v2/everything?
//q=apple&
//apiKey=f784d9b0fd504feb820b7d95221df3ff