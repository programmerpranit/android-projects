package com.example.barrywatch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.barrywatch.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button2 = findViewById<Button>(R.id.button2)

        button2.setOnClickListener{
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        }

    }
}