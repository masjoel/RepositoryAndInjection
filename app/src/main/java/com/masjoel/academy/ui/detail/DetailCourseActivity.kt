package com.masjoel.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.masjoel.academy.R
import com.masjoel.academy.data.CourseEntity
import com.masjoel.academy.ui.reader.CourseReaderActivity
import com.masjoel.academy.utils.DataDummy
import com.masjoel.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()
        //val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCourseViewModel::class.java]
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailCourseViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)
                progress_bar.visibility = View.VISIBLE
                viewModel.getModules().observe(this, Observer { modules ->
                    progress_bar.visibility = View.GONE
                    adapter.setModules(modules)
                    adapter.notifyDataSetChanged()
                })
                viewModel.getCourse().observe(this, Observer { course -> populateCourse(course) })

                /*val modules = viewModel.getModules()
                adapter.setModules(modules)
                populateCourse(viewModel.getCourse())*/
            }

            /*if (courseId != null) {
                val modules = DataDummy.generateDummyModules(courseId)
                adapter.setModules(modules)
                for(course in DataDummy.generateDummyCourses()) {
                    if(course.courseId == courseId) {
                        populateCourse(course)
                    }
                }
            }*/
        }

        with(rv_module) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        text_title.text = courseEntity.title
        text_desc.text = courseEntity.description
        text_date.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(image_poster)

        btn_start.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java).apply {
                putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            }
            startActivity(intent)
        }
    }

}