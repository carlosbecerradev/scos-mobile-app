package com.example.scos_mobile_app.ui.cliente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.scos_mobile_app.R
import com.example.scos_mobile_app.databinding.ActivityClienteBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ClienteActivity : AppCompatActivity() {

    lateinit var binding: ActivityClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomClienteNavigationView

        val navController = findNavController(R.id.fragmentContainerView2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeClienteFragment, R.id.nuevaOrdenFragment, R.id.encuestaFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}