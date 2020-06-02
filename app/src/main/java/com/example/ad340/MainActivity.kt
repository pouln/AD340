package com.example.ad340

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ad340.details.ForecastDetailsActivity
import com.example.ad340.forecast.CurrentForecastFragment
import com.example.ad340.forecast.CurrentForecastFragmentDirections
import com.example.ad340.location.LocationEntryFragment
import com.example.ad340.location.LocationEntryFragmentDirections
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), AppNavigator {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    // region setup methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle item selection
        return when (item.itemId){
            R.id.tempDisplaySetting -> {
                //do something
                showTempDisplaySettingDialog(this, tempDisplaySettingManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun navigateToCurrentForecast(zipcode: String) {
        val action = LocationEntryFragmentDirections.actionLocationEntryFragmentToCurrentForecastFragment2()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateToLocationEntry() {
        val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }


}
