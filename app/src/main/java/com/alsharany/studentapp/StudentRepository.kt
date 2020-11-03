package com.alsharany.studentapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.alsharany.criminalintent.database.StudentDatabase
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class StudentRepository private constructor(context: Context) {


    private val database: StudentDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            StudentDatabase::class.java,
            DATABASE_NAME
        )
            .build()
    private val studentDao = database.studentDao()
    private val executor = Executors.newSingleThreadExecutor()
    private val filesDir = context.applicationContext.filesDir
    fun getStudents(): LiveData<List<Student>> = studentDao.getStudents()
    fun getStudent(id: UUID): LiveData<Student?> = studentDao.getStudent(id)

    fun updateStudent(student: Student) {
        executor.execute {
            studentDao.updateStudent(student)
        }
    }

    fun addStudent(student: Student) {
        executor.execute {
            studentDao.addStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        executor.execute {
            studentDao.deleteStudent(student)
        }
    }
    // fun getPhotoFile(student: Student): File = File(filesDir, student.photoFileName)

    companion object {
        private var INSTANCE: StudentRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = StudentRepository(context)
            }
        }

        fun get(): StudentRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

}







