package com.example.scos_mobile_app.ui.cliente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.scos_mobile_app.R
import com.example.scos_mobile_app.databinding.ActivityClienteBinding
import com.example.scos_mobile_app.ui.auth.AuthActivity
import com.example.scos_mobile_app.ui.startNewActivity
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.top_menu_user, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_logout -> {
                Toast.makeText(this, "Cerrando la sesión!", Toast.LENGTH_SHORT).show()
                startNewActivity(AuthActivity::class.java)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}