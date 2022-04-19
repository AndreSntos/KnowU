package com.example.knowu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.knowu.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnOpenDialog: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnOpenDialog = findViewById(R.id.btnOpenBottomSheet)
    }

    fun abrirBottomSheet(view: View) {
        val bottomSheet: BottomSheet = BottomSheet()
        bottomSheet.show(supportFragmentManager, "TAG")
    }
}