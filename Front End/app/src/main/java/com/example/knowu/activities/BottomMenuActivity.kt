package com.example.knowu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.knowu.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class BottomMenuActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    private val homeFragment: HomeFragment = HomeFragment()
    private val chatFragment: ChatFragment = ChatFragment()
    private val eventoFragment: EventoFragment = EventoFragment()
    private val perfilFragment: PerfilFragment = PerfilFragment()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_menu)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment).commit()

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment).commit()
                    true
                }
                R.id.menu_chat -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, chatFragment).commit()
                    true
                }
                R.id.menu_evento -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, eventoFragment).commit()
                    true
                }
                R.id.menu_perfil -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, perfilFragment).commit()
                    true
                }
                else -> false
            }
        }

    }
}