package com.example.notey.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    
    @Insert
    suspend fun inert(note: Note)
    
    @Query("SELECT * FROM notes_table")
    fun  getAllNotes(): LiveData<List<Note>>
}