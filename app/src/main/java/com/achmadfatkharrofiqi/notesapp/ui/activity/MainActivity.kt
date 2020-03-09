package com.achmadfatkharrofiqi.notesapp.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.achmadfatkharrofiqi.notesapp.R
import com.achmadfatkharrofiqi.notesapp.model.Note
import com.achmadfatkharrofiqi.notesapp.ui.adapter.NoteAdapter
import com.achmadfatkharrofiqi.notesapp.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        // vars const
        private const val CREATE_NEW_NOTE = 1
    }

    // vars component
    private val btnCreate: FloatingActionButton by lazy { fab }
    private val recyclerView: RecyclerView by lazy { recycler_view }
    private val adapter: NoteAdapter by lazy { NoteAdapter(this@MainActivity) }
    private val noteViewModel: NoteViewModel by lazy { ViewModelProvider(this).get(NoteViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        btnCreate.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, CREATE_NEW_NOTE)
        }
    }

    private fun init(){
        recyclerView.apply {
            adapter         = this@MainActivity.adapter
            layoutManager   = LinearLayoutManager(this@MainActivity)
        }
        // observe the data changes
        observeData()
    }

    private fun observeData(){
        noteViewModel.allNotes.observe(this, Observer { notes ->
            adapter.setNotes(notes)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CREATE_NEW_NOTE && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewNoteActivity.EXTRA_REPLY)?.let {
                val note = Note(it)
                noteViewModel.insert(note)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}
