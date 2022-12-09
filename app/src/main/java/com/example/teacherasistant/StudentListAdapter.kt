package com.example.teacherasistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentListAdapter : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView
        val firstNameTextView: TextView
        val lastNameTextView: TextView

        init {
            idTextView = view.findViewById(R.id.student_list_item_id_textview)
            firstNameTextView = view.findViewById(R.id.student_list_item_firstname_textview)
            lastNameTextView = view.findViewById(R.id.student_list_item_lastname_textview)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.student_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 5
}