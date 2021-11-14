package com.example.whatsappstatussaver

import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.bumptech.glide.Glide
import java.io.File
import java.io.IOException

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)


        title = "Video"

        val statusVideo = findViewById<VideoView>(R.id.statusVideo)
        val chatV = findViewById<ImageView>(R.id.chatV)
        val downloadV = findViewById<ImageView>(R.id.downloadV)
        val shareV = findViewById<ImageView>(R.id.shareV)

        chatV.setOnClickListener{
            Toast.makeText(this, "chat Clicked", Toast.LENGTH_LONG).show()
        }

        shareV.setOnClickListener{
            Toast.makeText(this, "Share Clicked", Toast.LENGTH_LONG).show()
        }

        val destPath:String = intent.getStringExtra("destPath")!!
        val path = intent.getStringExtra("path")!!
        val fileName = intent.getStringExtra("fileName")
        val uri = intent.getStringExtra("uri")

//        val destPath1 = File(destPath)
//        val path1 = File(path)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(statusVideo)
        val uri1:Uri = Uri.parse(uri)
        statusVideo.setMediaController(mediaController)
        statusVideo.setVideoURI(uri1)
        statusVideo.requestFocus()
        statusVideo.start()


        downloadV.setOnClickListener{
            File(path).copyTo(File(destPath+fileName),true)

            MediaScannerConnection.scanFile(this, arrayOf(destPath+fileName), arrayOf("*/*"), null)

        }

    }
}