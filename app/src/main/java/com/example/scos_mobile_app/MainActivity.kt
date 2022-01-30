package com.example.scos_mobile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.scos_mobile_app.data.UserPreferences
import com.example.scos_mobile_app.ui.auth.AuthActivity
import com.example.scos_mobile_app.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.expiration.asLiveData().observe(this, Observer {

            val todayTime = (Math.floor((Date().time / 1000).toDouble())).toLong() //truncate millis
            Log.e("--------> Today: ", todayTime.toString())
            var timeToExpired = if(it == null) -1 else it.toLong() - todayTime
            Log.e("--------> Exp Seconds: ", timeToExpired.toString())
            var role = runBlocking { userPreferences.role.first() }
            val username = runBlocking { userPreferences.username.first() }
            Log.e("--------> Role: ", role.toString())
            Log.e("--------> Username: ", username.toString())

            if(timeToExpired < 60) {
                startNewActivity(AuthActivity::class.java)
            }

        })
    }

}