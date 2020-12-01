package com.masjoel.academy.ui.reader

import androidx.lifecycle.ViewModel
import com.masjoel.academy.data.ContentEntity
import com.masjoel.academy.data.ModuleEntity
import com.masjoel.academy.data.source.remote.AcademyRepository
import com.masjoel.academy.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): ArrayList<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): ModuleEntity = academyRepository.getContent(courseId, moduleId)

}

