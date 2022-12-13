package com.example.teacherasistant

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView

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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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

        // Adapters
        val existingStudentListAdapter = ExistingStudentListAdapter()

        val newStudentLinearLayout: LinearLayout = view.findViewById(R.id.new_student_linear_layout)
        val existingStudentRecyclerView: RecyclerView =
            view.findViewById(R.id.existing_students_recycler_view)
        existingStudentRecyclerView.adapter = existingStudentListAdapter


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

        return view
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