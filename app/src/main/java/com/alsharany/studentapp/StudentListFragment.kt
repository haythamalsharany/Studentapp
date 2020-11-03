package com.alsharany.studentapp

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class StudentListFragment : Fragment(), InputDialogFragmen.Callbacks {
    interface Listener {
        fun onStudentSelected(crimeId: UUID)
    }

    private lateinit var studentRecyclerView: RecyclerView
    private val studentViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(StudentViewModel::class.java)
    }
    private var adapter: StudentAdapter? = null
    private lateinit var noDataTextView: TextView
    private lateinit var addCrimeButton: Button
    private var listener: Listener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.student_list_fragment, container, false)
        studentRecyclerView = view.findViewById(R.id.Student_recycler_view) as RecyclerView
        noDataTextView = view.findViewById(R.id.empty_list_textview) as TextView
        addCrimeButton = view.findViewById(R.id.addCrimeBtn) as Button
        studentRecyclerView.layoutManager = LinearLayoutManager(context)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentViewModel.studentLiveDataList?.observe(

            viewLifecycleOwner,
            Observer { students ->

                updateUI(students)
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onStart() {
        super.onStart()
        addCrimeButton.setOnClickListener {
            InputDialogFragmen.newInstance().apply {
                setTargetFragment(this@StudentListFragment, 0)
                show(this@StudentListFragment.getParentFragmentManager(), "input")

            }
        }
    }

    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val st_number: TextView = itemView.findViewById(R.id.Student_number_tv)
        val st_name: TextView = itemView.findViewById(R.id.Student_name_tv)
        val st_passed: TextView = itemView.findViewById(R.id.Student_isPassed_tv)
        val deletImageButton = itemView.findViewById(R.id.delate_btn) as ImageButton
        private lateinit var student: Student

        init {
            itemView.setOnClickListener(this)
            deletImageButton.setOnClickListener {
                onStudentDelete(student)
            }
        }

        fun bind(item: Student) {

            this.student = item
            st_number.text = this.student.number.toString()
            st_name.text = this.student.name
            if (this.student.passed)
                st_passed.text = "passed "
            else
                st_passed.text = "faild "


        }

        override fun onClick(v: View?) {

            listener?.onStudentSelected(student.id)

        }

    }

    private inner class StudentAdapter(var students: List<Student>) :
        RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.item_list, parent, false)
            return StudentHolder(view)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = students[position]
            holder.bind(student)

        }

        override fun getItemCount(): Int {
            if (students.isNotEmpty()) {
                noDataTextView.visibility = View.GONE
                addCrimeButton.visibility = View.GONE
            } else {

                noDataTextView.visibility = View.VISIBLE
                addCrimeButton.visibility = View.VISIBLE
            }

            return students.size
        }


    }

    private fun updateUI(students: List<Student>) {


        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            StudentListFragment()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.student_menue, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_student -> {
                InputDialogFragmen.newInstance().apply {
                    setTargetFragment(this@StudentListFragment, 0)
                    show(this@StudentListFragment.getParentFragmentManager(), "input")
                }
                true
            }
            else ->
                return super.onOptionsItemSelected(item)


        }


    }

    override fun onStudentAdd(student: Student) {
        studentViewModel.addStudent(student)

    }

    override fun onStudentDelete(student: Student) {
        studentViewModel.deleteStudent(student)


    }
}
