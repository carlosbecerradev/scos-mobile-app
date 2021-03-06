package com.example.scos_mobile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.scos_mobile_app.data.UserPreferences
import com.example.scos_mobile_app.ui.auth.AuthActivity
import com.example.scos_mobile_app.ui.cliente.ClienteActivity
import com.example.scos_mobile_app.ui.startNewActivity
import com.example.scos_mobile_app.ui.tecnico.TecnicoActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val userPreferences = UserPreferences(this)

        userPreferences.expiration.asLiveData().observe(this, Observer {

            val todayTime = (Math.floor((Date().time / 1000).toDouble())).toLong() //truncate millis
            Log.e("--------> Today: ", todayTime.toString())
            var timeToExpired = if(it == null) -1 else it.toLong() - todayTime
            Log.e("--------> Exp Seconds: ", timeToExpired.toString())
            val role = runBlocking { userPreferences.role.first() }
            val username = runBlocking { userPreferences.username.first() }
            val sede = runBlocking { userPreferences.sede.first() }
            Log.e("--------> Role: ", role.toString())
            Log.e("--------> Username: ", username.toString())

            if(timeToExpired < 60) {
                startNewActivity(AuthActivity::class.java)
            }

            if(role.equals("CLIENTE")) {
               startNewActivity(ClienteActivity::class.java)
            }

            if(role.equals("TECNICO") || sede.equals("TECNICO")) {
                startNewActivity(TecnicoActivity::class.java)
            }

        })
    }

}