package com.achmadfatkharrofiqi.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.achmadfatkharrofiqi.notesapp.model.Note

class NoteAdapter(context: Context) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>()

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val noteItemView: TextView = itemView.findViewById(R.id.note_title)

        fun bindView(note: Note){
            noteItemView.text = note.note
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindView(notes[position])
    }

    fun setNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}