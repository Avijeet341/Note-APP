package com.avi.noteapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avi.noteapp.Adapters.NoteAdapter
import com.avi.noteapp.NoteApplication
import com.avi.noteapp.R
import com.avi.noteapp.ViewModel.NoteViewModel
import com.avi.noteapp.ViewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var  noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView =findViewById(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        val noteAdapter= NoteAdapter()
        recyclerView.adapter=noteAdapter

        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)

        noteViewModel= ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)

        noteViewModel.myAllNotes.observe(this) { notes ->
            noteAdapter.setNote(notes)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.new_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when(item.itemId){
             R.id.item_add_note->{
                 val  intent= Intent(this,NoteAddActivity::class.java)
                 startActivity(intent)
             }

             R.id.item_delete_all_note->Toast.makeText(application,
             "Delete icon was clicked",Toast.LENGTH_SHORT).show()
         }
        return true
    }

}