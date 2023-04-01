package com.example.mymvvpapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvpapp.data.db.SchoolDatabase
import com.example.mymvvpapp.data.model.Student
import com.example.mymvvpapp.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel() {

    val allStudents: Flow<List<Student>>

    init {
        allStudents = repository.allStudents
    }

    fun addNewStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewStudent(student = student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(student = student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStudent(student = student)
        }
    }

    fun deleteAllStudent() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStudents()
        }
    }
}