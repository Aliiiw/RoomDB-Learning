package com.example.mymvvpapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Students(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val family: String,
    val nationalCode: String,
    val grade: Grade
)


