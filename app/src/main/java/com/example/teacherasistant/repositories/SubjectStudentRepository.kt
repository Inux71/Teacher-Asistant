package com.example.teacherasistant.repositories

import com.example.teacherasistant.database.SubjectWithStudents
import com.example.teacherasistant.database.daos.SubjectDao
import kotlinx.coroutines.flow.Flow

class SubjectStudentRepository(private val _subjectDao: SubjectDao, subjectId: Long) {
    private val _subjectWithStudents: Flow<SubjectWithStudents> =
        _subjectDao.getSubjectWithStudentsById(subjectId)
    val subjectWithStudents: Flow<SubjectWithStudents>
        get() = _subjectWithStudents
}