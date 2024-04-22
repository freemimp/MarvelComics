package com.freemimp.main.data.di

import com.freemimp.main.data.RetrofitMarvelApi
import com.freemimp.main.data.MarvelApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMarvelApi(retrofitMarvelApi: RetrofitMarvelApi): MarvelApi
}
