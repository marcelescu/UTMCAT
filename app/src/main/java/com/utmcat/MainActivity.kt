package com.utmcat

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utmcat.data.api.ApiHelper
import com.utmcat.data.api.RetrofitBuilder
import com.utmcat.data.model.Student
import com.utmcat.ui.main.adapter.StudentAdapter
import com.utmcat.ui.main.viewmodel.CatViewModel
import com.utmcat.ui.main.viewmodel.ViewModelFactory
import com.utmcat.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CatViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(CatViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(arrayListOf()).apply {
                    itemClick = { student ->
                        var noteArray = arrayOf<String>()
                        for(nota in student.note)
                            noteArray = noteArray.plusElement(nota.materie + " " + nota.nota)
                        val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
                        with(alertDialogBuilder)
                        {
                            setTitle("Note student: " + student.nume)
                            setItems(noteArray) { dialog, which ->
                                Toast.makeText(applicationContext, noteArray[which], Toast.LENGTH_SHORT).show()
                            }

                            setNegativeButton("CANCEL", null)
                            show()
                        }
            }
        }
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getNote().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCES -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { studenti -> retrieveList(studenti) }
                    }
                    Status.EROARE -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.INCARCARE -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(studenti: List<Student>) {
        adapter.apply {
            addStudenti(studenti)
            notifyDataSetChanged()
        }
    }
}
