package com.masjoel.academy.ui.academy

import com.masjoel.academy.data.CourseEntity
import com.masjoel.academy.data.source.remote.AcademyRepository
import com.masjoel.academy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel
    /*@Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourses() {
        val courseEntities = viewModel.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }*/
    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        `when`<ArrayList<CourseEntity>>(academyRepository.getAllCourses()).thenReturn(DataDummy.generateDummyCourses())
        val courseEntities = viewModel.getCourses()
        verify<AcademyRepository>(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }

}