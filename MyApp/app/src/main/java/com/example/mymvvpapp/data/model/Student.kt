package com.example.mymvvpapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymvvpapp.utils.Constants.STUDENT_TABLE

@Entity(tableName = STUDENT_TABLE)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val family: String,
    val nationalCode: String,
    val grade: Grade
)


