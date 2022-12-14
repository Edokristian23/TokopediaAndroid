package com.example.tokopedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tokopedia.Login.LoginActivity
import com.example.tokopedia.databinding.ActivityNavigationBinding
import com.example.tokopedia.util.Prefs

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_keranjang
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {

            if (it.itemId == R.id.navigation_keranjang){

                val s = Prefs(this)
                if (s.getIsLogin()){
                    Log.d("TAG", "onCreate: ini keranjang")
                    navController.navigate(it.itemId)
                } else{
                    Log.d("TAG", "onCreate: anda belum login, silahkan login untuk mengakses keranjang")
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }else{
                navController.navigate(it.itemId)
                Log.d("TAG", "onCreate: ini bukan keranjang" + it.itemId)
            }

            return@setOnItemSelectedListener true
        }
    }
}