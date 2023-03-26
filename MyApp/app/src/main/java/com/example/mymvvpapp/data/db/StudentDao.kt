package com.example.mymvvpapp.data.db

import androidx.room.*
import com.example.mymvvpapp.data.model.Student
import com.example.mymvvpapp.utils.Constants.STUDENT_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    //crud works
    //Create -> @Insert
    //Read -> @Query
    //Update -> @Update
    //Delete -> @Delete

    @Query("SELECT * FROM $STUDENT_TABLE")
    fun getAllStudents(): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)


}