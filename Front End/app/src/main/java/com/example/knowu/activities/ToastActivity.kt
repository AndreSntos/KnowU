package com.example.knowu.activities

import android.graphics.BlurMaskFilter
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R


class ToastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)
        showToast()
    }

    fun showToast(){
        val view: View = layoutInflater.inflate(R.layout.activity_toast_copiado, null)
        val mToast = Toast(applicationContext)
        mToast.view = view
        mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        mToast.duration = Toast.LENGTH_LONG
        mToast.show()
    }
}