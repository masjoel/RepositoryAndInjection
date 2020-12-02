package com.masjoel.academy.di

import android.content.Context
import com.masjoel.academy.data.source.remote.AcademyRepository
import com.masjoel.academy.data.source.remote.RemoteDataSource
import com.masjoel.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }

}