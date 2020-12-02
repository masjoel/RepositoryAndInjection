package com.masjoel.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masjoel.academy.data.CourseEntity
import com.masjoel.academy.data.ModuleEntity
import com.masjoel.academy.data.source.remote.AcademyRepository
import com.masjoel.academy.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}

