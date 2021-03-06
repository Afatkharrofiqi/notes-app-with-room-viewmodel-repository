package com.achmadfatkharrofiqi.notesapp.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.achmadfatkharrofiqi.notesapp.R
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "com.achmadfatkharrofiqi.notesapp"
    }

    private val button: Button by lazy { button_save }
    private val editNote: EditText by lazy { edit_note }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNote.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val note = editNote.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, note)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
