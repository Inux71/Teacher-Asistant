package com.example.teacherasistant

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherasistant.database.entities.Student
import com.example.teacherasistant.database.entities.SubjectStudentCrossRef
import com.example.teacherasistant.viewmodels.StudentCreatorViewModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentCreatorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentCreatorFragment : Fragment() {
    private val _args: StudentCreatorFragmentArgs by navArgs()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var _studentCreatorViewModel: StudentCreatorViewModel
    private lateinit var _existingStudentListAdapter: ExistingStudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_creator, container, false)

        val subjectId: Long = _args.subjectId

        val newStudentLinearLayout: LinearLayout = view.findViewById(R.id.new_student_linear_layout)
        val existingStudentRecyclerView: RecyclerView =
            view.findViewById(R.id.existing_students_recycler_view)

        _studentCreatorViewModel = StudentCreatorViewModel(activity as Context, subjectId)
        _studentCreatorViewModel.students.observe(viewLifecycleOwner) {
            val existingStudents: List<Student> = it

            _studentCreatorViewModel.subjectWithStudents.observe(viewLifecycleOwner) {
                _existingStudentListAdapter =
                    ExistingStudentListAdapter(
                        existingStudents,
                        it.students,
                        _studentCreatorViewModel,
                        subjectId
                    )
                existingStudentRecyclerView.adapter = _existingStudentListAdapter
            }
        }

        val studentCreatorRadioGroup: RadioGroup =
            view.findViewById(R.id.student_creator_radio_group)
        studentCreatorRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.new_student_radio_button -> {
                    newStudentLinearLayout.visibility = View.VISIBLE
                    existingStudentRecyclerView.visibility = View.GONE
                }

                else -> {
                    existingStudentRecyclerView.visibility = View.VISIBLE
                    newStudentLinearLayout.visibility = View.GONE
                }
            }
        }

        val createStudentButton: Button = view.findViewById(R.id.student_creator_create_button)
        createStudentButton.setOnClickListener {
            val firstNameEditText: EditText =
                view.findViewById(R.id.student_creator_first_name_edit_text)
            val lastNameEditText: EditText =
                view.findViewById(R.id.student_creator_last_name_edit_text)
            val studentIdEditText: EditText =
                view.findViewById(R.id.student_creator_student_id_edit_text)

            if (firstNameEditText.text.toString().onlyLetters() && lastNameEditText.text.toString()
                    .onlyLetters() && studentIdEditText.text.toString().toIntOrNull() != null
            ) {
                _studentCreatorViewModel.students.observe(viewLifecycleOwner) {
                    if (studentExist(studentIdEditText.text.toString(), it)) {
                        Toast.makeText(context, "Student już istnieje!", Toast.LENGTH_SHORT).show()
                    } else {
                        val student = Student(
                            null,
                            studentIdEditText.text.toString(),
                            firstNameEditText.text.toString(),
                            lastNameEditText.text.toString()
                        )

                        lifecycleScope.launch {
                            val studentId: Long = _studentCreatorViewModel.insertStudent(student)
                            _studentCreatorViewModel.insertSubjectWithStudent(
                                SubjectStudentCrossRef(
                                    subjectId,
                                    studentId
                                )
                            )

                            view.findNavController().popBackStack()
                        }
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "Imię i nazwisko musi zawierać tylko litery, nr albumu musi zawierać tylko liczby!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }

    private fun String.onlyLetters(): Boolean = all { it.isLetter() }

    private fun studentExist(studentId: String, students: List<Student>): Boolean {
        students.forEach {
            if (it.studentId == studentId) {
                return true
            }
        }

        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentCreatorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentCreatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}