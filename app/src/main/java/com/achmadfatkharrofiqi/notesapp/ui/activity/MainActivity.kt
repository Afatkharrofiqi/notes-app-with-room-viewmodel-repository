package com.achmadfatkharrofiqi.notesapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.achmadfatkharrofiqi.notesapp.R
import com.achmadfatkharrofiqi.notesapp.ui.adapter.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { recycler_view }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter         =
                NoteAdapter(this@MainActivity)
            layoutManager   = LinearLayoutManager(this@MainActivity)
        }
    }
}