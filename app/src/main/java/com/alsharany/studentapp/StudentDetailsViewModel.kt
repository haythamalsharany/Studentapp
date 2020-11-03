package com.alsharany.studentapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class StudentDetailsViewModel : ViewModel() {
    val stRepository = StudentRepository.get()
    private var studentIdFromFragment = MutableLiveData<UUID>()
    fun getSudentIdFromFragment(uuid: UUID) {
        studentIdFromFragment.value = uuid
    }

    val StudentFromDatabase: LiveData<Student> =
        Transformations.switchMap(studentIdFromFragment) { studentId ->

            stRepository.getStudent(studentId)
        }

    fun updateStudent(student: Student) {
        stRepository.updateStudent(student)
    }


}
