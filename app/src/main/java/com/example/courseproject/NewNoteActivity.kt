package com.example.courseproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.View
import androidx.core.text.set
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        sharedPreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

        window.decorView.setBackgroundColor(sharedPreferences.getInt("BACKGROUND_COLOR", Color.WHITE))

        yourTitleText.editableText.append(sharedPreferences.getString("TITLE_TEXT", ""))
        yourText.editableText.append(sharedPreferences.getString("FIELD_TEXT", ""))
        yourText.setTypeface(Typeface.DEFAULT, sharedPreferences.getInt("TYPEFACE", Typeface.NORMAL))
        yourText.paintFlags = sharedPreferences.getInt("UNDERLINE", 0)

        createNoteButton.setOnClickListener {
            var saveText = Intent(this, MainActivity::class.java)

            val savedFieldText: String = yourText.editableText.toString()
            val savedTitleText: String = yourTitleText.editableText.toString()
            val savedTypeFace: Int = yourText.typeface.style
            val savedUnderline: Int = yourText.paintFlags

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("FIELD_TEXT", savedFieldText)
            editor.putString("TITLE_TEXT", savedTitleText)
            editor.putInt("TYPEFACE", savedTypeFace)
            editor.putInt("UNDERLINE", savedUnderline)
            editor.apply()

            saveText.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(saveText)
        }
    }

    fun goback(view: View) {
        onBackPressed()
    }

    fun bold(view: View) {
        if (yourText.typeface.style == Typeface.BOLD_ITALIC) {
            yourText.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
        }
        else if (yourText.typeface.isItalic) {
            yourText.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
        }
        else if (yourText.typeface.isBold) {
            yourText.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
        }
        else {
            yourText.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        }
    }

    fun italic(view: View) {
        if (yourText.typeface.style == Typeface.BOLD_ITALIC) {
            yourText.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        }
        else if (yourText.typeface.isBold) {
            yourText.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
        }
        else if (yourText.typeface.isItalic){
            yourText.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
        }
        else {
            yourText.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
        }
    }

    fun underline(view: View) {
        if (yourText.paintFlags == Paint.UNDERLINE_TEXT_FLAG){
            yourText.paintFlags = 0
        }
        else {
            yourText.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }
    }
}