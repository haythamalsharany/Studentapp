package com.alsharany.studentapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

import com.alsharany.studentapp.Student
import java.util.*

@Dao
interface StudentDao {
    @Query("SELECT* FROM  student_table")
    fun getStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM Student_table WHERE id=(:id)")
    fun getStudent(id: UUID): LiveData<Student?>

    @Update
    fun updateStudent(student: Student)

    @Insert
    fun addStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)
}