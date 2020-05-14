package com.utmcat.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utmcat.R
import com.utmcat.data.model.Student
import kotlinx.android.synthetic.main.item_layout.view.*


class StudentAdapter(private val studenti: ArrayList<Student>) : RecyclerView.Adapter<StudentAdapter.DataViewHolder>() {

    var itemClick: ((Student) -> Unit)? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemClick: ((Student) -> Unit)? = null

        @SuppressLint("ResourceAsColor")
        fun bind(student: Student) {
            itemView.apply {
                textNumeStudent.text = student.nume
                textIdStudent.text = "Grupa " + student.grupa

                val status: TextView = findViewById<TextView>(R.id.textStatus)

                if (!student.note.any { nota -> nota.nota.toInt() < 5 })
                {
                    status.setBackgroundResource(R.color.status_1);
                }
                else if (!student.note.any { nota -> nota.nota.toInt() >= 5 })
                {
                    status.setBackgroundResource(R.color.status_3);
                }
                else
                {
                    status.setBackgroundResource(R.color.status_2);
                }

                itemView?.setOnClickListener {
                    itemClick?.invoke(student)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)).apply {
            itemClick = { student ->
                this@StudentAdapter .itemClick?.invoke(student)
            }
        }

    override fun getItemCount(): Int = studenti.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(studenti[position])
    }

    fun addStudenti(studenti: List<Student>) {
        this.studenti.apply {
            clear()
            addAll(studenti.sortedBy { student -> student.nume })
        }

    }
}