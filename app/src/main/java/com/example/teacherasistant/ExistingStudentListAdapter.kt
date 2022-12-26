package com.example.teacherasistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherasistant.database.entities.Student

class ExistingStudentListAdapter(private val _data: List<Student>) :
    RecyclerView.Adapter<ExistingStudentListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView
        val firstNameTextView: TextView
        val lastNameTextView: TextView
        val addButton: Button

        init {
            idTextView = view.findViewById(R.id.existing_student_list_item_id_textview)
            firstNameTextView =
                view.findViewById(R.id._existing_student_list_item_firstname_textview)
            lastNameTextView = view.findViewById(R.id.existing_student_list_item_lastname_textview)
            addButton = view.findViewById(R.id.existing_student_list_item_add_button)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.existing_student_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.idTextView.text = _data[position].studentId
        viewHolder.firstNameTextView.text = _data[position].firstName
        viewHolder.lastNameTextView.text = _data[position].lastName
        viewHolder.addButton.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = _data.size
}