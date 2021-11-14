package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesAdapter {

    private lateinit var viewModel: NoteViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        recyclerView.adapter =adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, {List ->
            List?.let {
                adapter.updateList(it)
            }

        })

        val submit = findViewById<Button>(R.id.button)
        submit.setOnClickListener{
            submitData()
        }

    }



    override fun onItemClicked(notes: Notes) {
        viewModel.deleteNote(notes)
    }

    private fun submitData() {
        val input = findViewById<EditText>(R.id.input)
        val noteText = input.editableText.toString()
        if (noteText.isNotEmpty()){
            viewModel.insertNote(Notes(noteText))

        }
    }

//    fun submitData(view: View) {
//        val noteText = input.text.toString()
//        if (noteText.isNotEmpty()){
//            viewModel.insertNote(Notes(noteText))
//            Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
//        }
//    }



}