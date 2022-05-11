package com.example.knowu.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.example.knowu.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.page_1 -> {
//                item.isChecked = true
//                startActivity(Intent(baseContext, HomeEvent::class.java))
//                true
//            }
//            R.id.page_2 -> {
//                item.isChecked = true
//                true
//            }
//            R.id.page_3 -> {
//                item.isChecked = true
//                startActivity(Intent(baseContext, EventActivity::class.java))
//                true
//            }
//            R.id.page_4 -> {
//                item.isChecked = true
//                startActivity(Intent(baseContext, ProfileActivity::class.java))
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}