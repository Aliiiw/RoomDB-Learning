package com.example.mymvvpapp.data.repository

import com.example.mymvvpapp.data.db.StudentDao
import com.example.mymvvpapp.data.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {

    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

}