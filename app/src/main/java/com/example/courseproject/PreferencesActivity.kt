package com.example.courseproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_note.*
import kotlinx.android.synthetic.main.activity_preferences.*

class PreferencesActivity : AppCompatActivity() {

    var bgColor = Color.WHITE

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        preferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

        window.decorView.setBackgroundColor(preferences.getInt("BACKGROUND_COLOR", Color.WHITE))
        bgColor = preferences.getInt("BACKGROUND_COLOR", Color.WHITE)

        backButton2.setOnClickListener {
            onBackPressed()
        }

        whiteBtn.setOnClickListener {
            window.decorView.setBackgroundColor(Color.WHITE)
            bgColor = Color.WHITE
        }
        blueBtn.setOnClickListener {
            window.decorView.setBackgroundColor(Color.CYAN)
            bgColor = Color.CYAN
        }
        yellowBtn.setOnClickListener {
            window.decorView.setBackgroundColor(Color.YELLOW)
            bgColor = Color.YELLOW
        }
        grayBtn.setOnClickListener {
            window.decorView.setBackgroundColor(Color.LTGRAY)
            bgColor = Color.LTGRAY
        }

        ClearNotesButton.setOnClickListener {
            var clearIntent = Intent(this, MainActivity::class.java)

            val savedFieldText: String = ""
            val savedTitleText: String = ""
            val savedTypeFace: Int = Typeface.NORMAL
            val savedUnderline: Int = 0
            val savedBGColor: Int = bgColor

            val editor: SharedPreferences.Editor = preferences.edit()
            editor.putString("FIELD_TEXT", savedFieldText)
            editor.putString("TITLE_TEXT", savedTitleText)
            editor.putInt("TYPEFACE", savedTypeFace)
            editor.putInt("UNDERLINE", savedUnderline)
            editor.putInt("BACKGROUND_COLOR", savedBGColor)
            editor.apply()

            clearIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(clearIntent)
        }

        DefaultSettingsButton.setOnClickListener {
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            var clearIntent = Intent(this, MainActivity::class.java)

            clearIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(clearIntent)
        }
    }

    override fun onBackPressed() {
        var colorIntent = Intent(this, MainActivity::class.java)
        val savedBGColor: Int = bgColor

        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putInt("BACKGROUND_COLOR", savedBGColor)
        editor.apply()

        colorIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(colorIntent)
    }
}

