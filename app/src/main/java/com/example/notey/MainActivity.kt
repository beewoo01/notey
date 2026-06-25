package com.example.notey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.example.notey.repository.NotesRepository
import com.example.notey.roomdb.Note
import com.example.notey.roomdb.NotesDB
import com.example.notey.screens.DisplayNotesList
import com.example.notey.ui.theme.NoteyTheme
import com.example.notey.viewmodel.NoteViewModel
import com.example.notey.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Room DB
        val database = NotesDB.getInstance(applicationContext)

        val repository = NotesRepository(database.notesDao)

        val vieWModelFactory = NoteViewModelFactory(repository)

        val noteViewModel = ViewModelProvider(
            this,
            vieWModelFactory
        )[NoteViewModel::class.java]

        val note1 = Note(
            0,
            "This is a Demo Note",
            "Welcome my friends, Please Rate us 5 stars" + "to continue updating this course...",
            "#f59597".toColorInt(),
        )

        noteViewModel.insert(note1)

        setContent {
            NoteyTheme {


                val notes by noteViewModel.allNotes.observeAsState(emptyList())

                DisplayNotesList(notes = notes)

            }
        }
    }
}
