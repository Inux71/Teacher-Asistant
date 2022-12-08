package com.example.teacherasistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubjectListAdapter() :
    RecyclerView.Adapter<SubjectListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayTextView: TextView
        val timeTextView: TextView
        val nameTextView: TextView

        init {
            dayTextView = view.findViewById(R.id.subject_list_item_day_textview)
            timeTextView = view.findViewById(R.id.subject_list_item_time_textview)
            nameTextView = view.findViewById(R.id.subject_list_item_name_textview)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.subject_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 5
}