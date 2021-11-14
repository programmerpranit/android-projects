package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val msg = intent.getStringExtra("name")

        val birthdayGreet = findViewById<TextView>(R.id.birthdayGreet)
        birthdayGreet.text = "Good Morning\n $msg"
    }



}