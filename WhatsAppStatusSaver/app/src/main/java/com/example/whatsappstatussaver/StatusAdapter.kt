package com.example.whatsappstatussaver

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatsappstatussaver.utils.Utils


class StatusAdapter(private val context:Context, private val fileList:ArrayList<Model>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.status_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = fileList[position]

        if(currentItem.uri.toString().endsWith(".mp4")){
            holder.videoButton.visibility = View.VISIBLE
        }

        Glide.with(holder.itemView.context).load(currentItem.uri).into(holder.statusThumbnail)

        holder.statusThumbnail.setOnClickListener{
            if (currentItem.uri.toString().endsWith(".mp4")){
                val destinationPath:String = Environment.getExternalStorageDirectory().absolutePath+Utils.WHATSAPP_STATUSES_SAVED_LOCATION
                val intent1 = Intent(context, VideoActivity::class.java)
                intent1.putExtra("destPath", destinationPath)
                intent1.putExtra("path", currentItem.path)
                intent1.putExtra("fileName", currentItem.fileName)
                intent1.putExtra("uri", currentItem.uri.toString())
                context.startActivity(intent1)

            }
            else{
                val destinationPath:String = Environment.getExternalStorageDirectory().absolutePath+Utils.WHATSAPP_STATUSES_SAVED_LOCATION
                val intent2 = Intent(context, PicturesActivity::class.java)
                intent2.putExtra("destPath", destinationPath)
                intent2.putExtra("path", currentItem.path)
                intent2.putExtra("fileName", currentItem.fileName)
                intent2.putExtra("uri", currentItem.uri.toString())
                context.startActivity(intent2)
            }
        }

    }

    override fun getItemCount(): Int {
        return fileList.size
    }


}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val statusThumbnail:ImageView = itemView.findViewById(R.id.statusThumbnail)
    val videoButton:ImageView = itemView.findViewById(R.id.videoButton)
}