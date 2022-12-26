package com.example.teacherasistant.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.teacherasistant.database.AppDatabase
import com.example.teacherasistant.database.entities.Student
import com.example.teacherasistant.repositories.StudentRepository

class StudentCreatorViewModel(private val _context: Context) : ViewModel() {
    private val _studentRepository =
        StudentRepository(AppDatabase.getInstance(_context).studentDao())

    private val _students: LiveData<List<Student>> = _studentRepository.students.asLiveData()
    val students: LiveData<List<Student>>
        get() = _students
}