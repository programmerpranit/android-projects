package com.example.whatsappstatussaver

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.whatsappstatussaver.utils.Utils
import java.io.File

class MainActivity : AppCompatActivity() {

    private val requestCode = 1

    private val fileslist:ArrayList<Model> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()


        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe)

        refreshLayout.setOnRefreshListener {
            setupLayout()
        }
    }


    private fun checkPermission() {
        if (SDK_INT>23){

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE )==PackageManager.PERMISSION_GRANTED){
                setupLayout()
            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), requestCode)
            }

        }
        else{
            setupLayout()
        }
    }

    private fun setupLayout() {
        fileslist.clear()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val adapter:StatusAdapter = StatusAdapter(this, getData())
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getData(): ArrayList<Model> {
        var f:Model
        val targetPath: String = Environment.getExternalStorageDirectory().absolutePath+Utils.WHATSAPP_STATUSES_LOCATION
        val targetDir = File(targetPath)
        val files: Array<out File>? = targetDir.listFiles()

        if (files != null) {
            for(file in files){
                f = Model(uri = Uri.fromFile(file), path = file.absolutePath, fileName = file.name)
                if (!Uri.fromFile(file).toString().endsWith(".nomedia")){
                    fileslist.add(f)
                }
            }
        }
        else{
            Toast.makeText(this, "No Statuses", Toast.LENGTH_LONG).show()
        }
        return fileslist
    }


}


