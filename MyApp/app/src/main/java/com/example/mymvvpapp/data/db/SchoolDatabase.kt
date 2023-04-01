package com.example.mymvvpapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymvvpapp.data.model.Student
import com.example.mymvvpapp.utils.Constants.DATABASE_NAME

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class SchoolDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

}