package com.example.teacherasistant.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.teacherasistant.database.AppDatabase
import com.example.teacherasistant.database.SubjectWithStudents
import com.example.teacherasistant.repositories.SubjectStudentRepository

class StudentListViewModel(private val _context: Context, subjectId: Long) : ViewModel() {
    private val _subjectStudentRepository =
        SubjectStudentRepository(AppDatabase.getInstance(_context).subjectDao(), subjectId)

    private val _subjectWithStudents: LiveData<SubjectWithStudents> =
        _subjectStudentRepository.subjectWithStudents.asLiveData()
    val subjectWithStudents: LiveData<SubjectWithStudents>
        get() = _subjectWithStudents
}