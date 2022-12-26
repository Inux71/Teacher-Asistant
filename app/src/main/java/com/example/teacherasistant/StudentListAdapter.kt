package com.example.teacherasistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherasistant.database.entities.Student

class StudentListAdapter(private val _data: List<Student>) :
    RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView
        val firstNameTextView: TextView
        val lastNameTextView: TextView
        val goToButton: Button

        init {
            idTextView = view.findViewById(R.id.student_list_item_id_textview)
            firstNameTextView = view.findViewById(R.id.student_list_item_firstname_textview)
            lastNameTextView = view.findViewById(R.id.student_list_item_lastname_textview)
            goToButton = view.findViewById(R.id.student_list_item_go_to_button)
            goToButton.setOnClickListener {
                val action =
                    StudentListFragmentDirections.actionStudentListFragmentToMarkListFragment2()

                view.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.student_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.idTextView.text = _data[position].studentId
        viewHolder.firstNameTextView.text = _data[position].firstName
        viewHolder.lastNameTextView.text = _data[position].lastName
    }

    override fun getItemCount(): Int = _data.size
}