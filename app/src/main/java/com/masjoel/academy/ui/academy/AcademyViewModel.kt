package com.masjoel.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.masjoel.academy.data.CourseEntity
import com.masjoel.academy.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}
