package com.cebrailyilmaz.todoapplication

import android.content.Context
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cebrailyilmaz.todoapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityBinding.root
        setContentView(view)


        /*
        activityBinding.toolbar.setNavigationIcon(R.drawable.ic_baseline_nav_left_24)
        val navController = findNavController(R.id.fragment_container)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        activityBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
         */

        //setupActionBarWithNavController(findNavController( R.id.fragment_container))

    }


 /*
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
  */
}