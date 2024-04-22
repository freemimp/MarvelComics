package com.freemimp.main.data.di

import com.freemimp.main.BuildConfig
import com.freemimp.main.data.interceptors.AuthInterceptor
import com.freemimp.main.data.MarvelApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun provideMarvelService(
        okHttpClient: OkHttpClient
    ): MarvelApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }

    @Provides
    fun provideOkhttpClient(
        interceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    fun provideAuthInterceptor() = AuthInterceptor(BuildConfig.privateApiKey, BuildConfig.publicApiKey)

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}
