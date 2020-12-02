package com.masjoel.academy.ui.academy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.masjoel.academy.R
import com.masjoel.academy.utils.DataDummy
import com.masjoel.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_academy.*


class AcademyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_academy, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            //val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[AcademyViewModel::class.java]
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]
            val courses = viewModel.getCourses()

            val academyAdapter = AcademyAdapter()
            //academyAdapter.setCourses(courses)
            progress_bar.visibility = View.VISIBLE
            viewModel.getCourses().observe(viewLifecycleOwner, Observer{ courses ->
                progress_bar.visibility = View.GONE
                academyAdapter.setCourses(courses)
                academyAdapter.notifyDataSetChanged()
            })

            with(rv_academy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }
}
