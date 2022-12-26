package com.example.teacherasistant.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.teacherasistant.database.SubjectWithStudents
import com.example.teacherasistant.database.entities.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject_table")
    fun getAllSubjects(): Flow<List<Subject>>

    @Transaction
    @Query("SELECT * FROM subject_table WHERE id LIKE :id")
    fun getSubjectWithStudentsById(id: Long): Flow<SubjectWithStudents>

    @Insert
    suspend fun insertSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)
}