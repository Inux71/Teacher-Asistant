package com.example.teacherasistant

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherasistant.viewmodels.MarkListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MarkListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarkListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var _markListViewModel: MarkListViewModel
    private lateinit var _markListAdapter: MarkListAdapter

    private var _subjectId: Long? = null
    private var _studentId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments?.let {
            MarkListFragmentArgs.fromBundle(it)
        }

        if (args != null) {
            _subjectId = args.subjectId
            _studentId = args.studentId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mark_list, container, false)

        val markListRecyclerView: RecyclerView = view.findViewById(R.id.mark_list_recycler_view)

        _markListViewModel = MarkListViewModel(activity as Context, _subjectId!!, _studentId!!)
        _markListViewModel.marks.observe(viewLifecycleOwner) {
            _markListAdapter = MarkListAdapter(it, _markListViewModel)
            markListRecyclerView.adapter = _markListAdapter
        }

        val addMarkButton: Button = view.findViewById(R.id.add_mark_button)
        addMarkButton.setOnClickListener {
            val action = MarkListFragmentDirections.actionMarkListFragmentToMarkCreatorFragment(
                _subjectId!!,
                _studentId!!
            )

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
         * @return A new instance of fragment MarkListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MarkListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}