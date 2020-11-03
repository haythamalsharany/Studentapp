package com.alsharany.studentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), StudentListFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = StudentListFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment
            ).commit()
        }
    }

    override fun onStudentSelected(studentId: UUID) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = StudentDetailsFragment.newInstance(studentId)
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment
            ).commit()
        }

    }
}