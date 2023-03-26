package com.example.mymvvpapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvpapp.data.db.SchoolDatabase
import com.example.mymvvpapp.data.model.Student
import com.example.mymvvpapp.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    val allStudents: Flow<List<Student>>
    private val repository: StudentRepository

    init {
        val studentDao = SchoolDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao = studentDao)
        allStudents = repository.allStudents
    }

    fun addNewStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewStudent(student = student)
        }
    }
}