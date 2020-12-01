package com.masjoel.academy.ui.bookmark

import com.masjoel.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
