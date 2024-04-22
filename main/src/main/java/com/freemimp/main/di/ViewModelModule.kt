package com.freemimp.main.di

import com.freemimp.main.data.MarvelRepositoryImpl
import com.freemimp.main.domain.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent



@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindMarvelRepository(impl: MarvelRepositoryImpl): MarvelRepository
}
