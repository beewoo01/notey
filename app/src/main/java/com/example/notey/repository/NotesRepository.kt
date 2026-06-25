package com.example.notey.repository

import androidx.lifecycle.LiveData
import com.example.notey.roomdb.Note
import com.example.notey.roomdb.NoteDao

class NotesRepository(private val noteDao: NoteDao) {

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        return noteDao.insert(note)
    }

}