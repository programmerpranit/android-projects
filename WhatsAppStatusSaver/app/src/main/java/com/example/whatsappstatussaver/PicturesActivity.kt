package com.example.whatsappstatussaver

import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.whatsappstatussaver.utils.Utils
import java.io.*
import java.util.*


class PicturesActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)

        title = "Picture"

        val statusImage = findViewById<ImageView>(R.id.statusImage)
        val chat = findViewById<ImageView>(R.id.chat)
        val download = findViewById<ImageView>(R.id.download)
        val share = findViewById<ImageView>(R.id.share)

        chat.setOnClickListener{
            Toast.makeText(this, "chat Clicked", Toast.LENGTH_LONG).show()
        }



        val destPath:String = intent.getStringExtra("destPath")!!
        val path: String = intent.getStringExtra("path")!!
        val fileName = intent.getStringExtra("fileName")!!
        val uri2 = intent.getStringExtra("uri")

        val destPath1 = File(destPath)
        val path1 = File(path)
        val urip: Uri = Uri.parse(uri2)
        Glide.with(this).load(uri2).into(statusImage)


        download.setOnClickListener{

            Toast.makeText(this, "Download Clicked", Toast.LENGTH_SHORT).show()

            File(path).copyTo(File(destPath+fileName),true)


            MediaScannerConnection.scanFile(this, arrayOf(destPath+fileName), arrayOf("*/*"), null)
//
        }

        share.setOnClickListener{
            val sIntent = Intent(Intent.ACTION_SEND)
                .putExtra("uri", urip)
            startActivity(sIntent)
        }
//


    }



}