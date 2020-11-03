package com.alsharany.studentapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Student_table")
data class Student(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    var number: Int = 0,
    var name: String = "",
    var passed: Boolean = false
)
