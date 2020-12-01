package com.masjoel.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.masjoel.academy.data.CourseEntity
import com.masjoel.academy.utils.DataDummy

class BookmarkViewModel : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}
