package com.example.courseproject

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HelpActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        sharedPreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

        window.decorView.setBackgroundColor(sharedPreferences.getInt("BACKGROUND_COLOR", Color.WHITE))
    }

    fun GoBack(view: View) {
        onBackPressed()
    }
}