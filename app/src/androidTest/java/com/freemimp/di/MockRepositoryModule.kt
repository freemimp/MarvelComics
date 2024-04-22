package com.freemimp.di

import com.freemimp.main.di.ViewModelModule
import com.freemimp.main.domain.MarvelRepository
import com.freemimp.main.domain.model.Comic
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.mockk
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ViewModelModule::class]
)
@Module
object MockRepositoryModule {

    @Singleton
    @Provides
    fun provideMockRepository(): MarvelRepository {
        return mockk<MarvelRepository>(relaxed = true).apply {
            coEvery { this@apply.getComics() } returns Result.success((1..20).map {
                Comic(
                    id = "$it",
                    title = "Comics nr $it",
                    thumbnailUrl = "url",
                    fullImageUrl = "url"
                )
            })
        }
    }
}
