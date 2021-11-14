package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getMessage(view:View){

        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString()



        val intent = Intent(this, DisplayMessageActivity::class.java)
        intent.putExtra("name", message)

        startActivity(intent)
    }

}