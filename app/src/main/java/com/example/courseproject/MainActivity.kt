package com.example.courseproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_note.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

        window.decorView.setBackgroundColor(sharedPreferences.getInt("BACKGROUND_COLOR", Color.WHITE))

        noteText.text = sharedPreferences.getString("FIELD_TEXT", "")
        titleText.text = sharedPreferences.getString("TITLE_TEXT", "")

        noteText.setTypeface(Typeface.DEFAULT, sharedPreferences.getInt("TYPEFACE", Typeface.NORMAL))
        noteText.paintFlags = sharedPreferences.getInt("UNDERLINE", 0)


        helpButton.setOnClickListener {
            var helpintent = Intent(this, HelpActivity::class.java)
            startActivity(helpintent)
        }

        newnoteButton.setOnClickListener {
            var newNoteIntent = Intent(this, NewNoteActivity::class.java)
            startActivity(newNoteIntent)
        }

        settingsButton.setOnClickListener {
            var preferenceIntent = Intent(this, PreferencesActivity::class.java)
            startActivity(preferenceIntent)
        }
    }
}