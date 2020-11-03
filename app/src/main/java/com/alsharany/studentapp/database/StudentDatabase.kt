package com.alsharany.criminalintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


import com.alsharany.studentapp.Student
import com.alsharany.studentapp.database.StudentDao

@Database(entities = [Student::class], version = 1, exportSchema = false)
@TypeConverters(StudentTypeConverter::class)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

}





