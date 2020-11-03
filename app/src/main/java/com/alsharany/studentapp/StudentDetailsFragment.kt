package com.alsharany.studentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

private const val STUDENT_ID = "studentId"

class StudentDetailsFragment : Fragment() {

    private lateinit var student: Student
    private var student_id: UUID? = null
    lateinit var studentNoEditText: EditText
    lateinit var studentNameEditText: EditText
    lateinit var studentPassedCheckBox: CheckBox
    lateinit var studentPassedTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        student = Student()
        arguments?.let {
            student_id = it.getSerializable(STUDENT_ID) as UUID


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)
        studentNoEditText = view.findViewById(R.id.St_no_ed)
        studentNameEditText = view.findViewById(R.id.St_name_ed)
        studentPassedCheckBox = view.findViewById(R.id.st_ispassed_chb)
        studentPassedTextView = view.findViewById(R.id.Student_isPassed_tv)
        return view
    }

    companion object {


        fun newInstance(studentId: UUID) =
            StudentDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(STUDENT_ID, studentId)

                }
            }
    }
}