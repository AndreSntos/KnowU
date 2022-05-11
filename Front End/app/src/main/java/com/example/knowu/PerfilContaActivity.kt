package com.example.knowu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.knowu.adapters.PerfilContaAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PerfilContaActivity : AppCompatActivity() {

    var tabTitle = arrayOf("Perfil", "Conta")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_conta)

        var pager = findViewById<ViewPager2>(R.id.viewPager2)
        var tl = findViewById<TabLayout>(R.id.tabLayout)
        pager.adapter = PerfilContaAdapter(supportFragmentManager, lifecycle)


        TabLayoutMediator(tl, pager) {
            tab, position ->
                tab.text = tabTitle[position]
        }.attach()
    }
}