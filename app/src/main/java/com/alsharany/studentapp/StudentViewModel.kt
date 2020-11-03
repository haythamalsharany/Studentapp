package com.alsharany.studentapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private var studentRepo: StudentRepository? = null
    var studentLiveDataList: LiveData<List<Student>>? = null

    init {
        studentRepo = StudentRepository.get()


        studentLiveDataList = studentRepo!!.getStudents()
    }


    fun addStudent(student: Student) {
        studentRepo?.addStudent(student)

    }

    fun deleteStudent(student: Student) {
        studentRepo?.deleteStudent(student)


    }


}