package com.example.ad340

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private val forecastRepository = ForecastRepository()
    // region setup methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText: EditText = findViewById(R.id.zipcodeEditText)
        val enterButton: Button = findViewById(R.id.enterButton)

        enterButton.setOnClickListener {
            val zipcode: String = zipcodeEditText.text.toString()
            if(zipcode.length != 5){
                Toast.makeText(this, R.string.zipcode_entry_error, Toast.LENGTH_SHORT).show()
            }else {
                forecastRepository.loadForecast(zipcode)
            }
        }
        val forecastList: RecyclerView = findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter = DailyForecastAdapter(){forecastItem ->
            val msg = getString(R.string.forecast_click_format, forecastItem.temp, forecastItem.description)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        forecastList.adapter = dailyForecastAdapter

        val weeklyForecastObserver = Observer<List<DailyForecast>>{forecastItems ->
            // update our list adapter
            dailyForecastAdapter.submitList(forecastItems)

        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }


    /* Not using these lifecycle methods for this week (3rd week)
    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    // endregion setup methods

    // region teardown methods

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    */

    // endregion teardown methods
}
