package com.example.knowu

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EventClickedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_clicked)

        val id = intent.getIntExtra("idEvento", 0)
        println(id)
        findViewById<TextView>(R.id.tvIdEvento).text = id.toString()
    }
}