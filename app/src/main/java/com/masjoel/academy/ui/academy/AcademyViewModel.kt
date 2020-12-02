package com.masjoel.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masjoel.academy.data.CourseEntity
import com.masjoel.academy.data.source.remote.AcademyRepository
import com.masjoel.academy.utils.DataDummy

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()

}
