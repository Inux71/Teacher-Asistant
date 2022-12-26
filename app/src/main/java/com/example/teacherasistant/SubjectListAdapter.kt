package com.example.teacherasistant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherasistant.database.entities.Subject

class SubjectListAdapter(private val _data: List<Subject>, private val _context: Context) :
    RecyclerView.Adapter<SubjectListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayTextView: TextView
        val timeTextView: TextView
        val nameTextView: TextView
        val goToButton: Button

        init {
            dayTextView = view.findViewById(R.id.subject_list_item_day_textview)
            timeTextView = view.findViewById(R.id.subject_list_item_time_textview)
            nameTextView = view.findViewById(R.id.subject_list_item_name_textview)
            goToButton = view.findViewById(R.id.subject_list_item_go_to_button)
            goToButton.setOnClickListener {
                val action =
                    SubjectListFragmentDirections.actionSubjectListFragmentToStudentListFragment()

                view.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.subject_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.dayTextView.text = _data[position].day
        viewHolder.timeTextView.text = _context.getString(
            R.string.subject_list_item_time_placeholder,
            _data[position].startTime,
            _data[position].endTime
        )
        viewHolder.nameTextView.text = _data[position].name
    }

    override fun getItemCount(): Int = _data.size
}