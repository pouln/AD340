package com.example.ad340

interface AppNavigator {

    fun navigateToCurrentForecast(zipcode: String)
    fun navigateToLocationEntry()
    fun navigateToForecastDetails(forecast: DailyForecast)
}