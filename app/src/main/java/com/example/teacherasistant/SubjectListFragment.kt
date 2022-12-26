package com.example.teacherasistant

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherasistant.viewmodels.SubjectListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubjectListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubjectListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var _subjectListViewModel: SubjectListViewModel
    private lateinit var _subjectListAdapter: SubjectListAdapter

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
        val view = inflater.inflate(R.layout.fragment_subject_list, container, false)

        val subjectListRecyclerView: RecyclerView =
            view.findViewById(R.id.subject_list_recycler_view)

        _subjectListViewModel = SubjectListViewModel(activity as Context)
        _subjectListViewModel.subjects.observe(viewLifecycleOwner) {
            _subjectListAdapter = SubjectListAdapter(it, activity as Context)
            subjectListRecyclerView.adapter = _subjectListAdapter
        }

        val addSubjectButton: Button = view.findViewById(R.id.add_subject_button)
        addSubjectButton.setOnClickListener {
            val action =
                SubjectListFragmentDirections.actionSubjectListFragmentToSubjectCreatorFragment()

            view.findNavController().navigate(action)
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
         * @return A new instance of fragment SubjectListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubjectListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}