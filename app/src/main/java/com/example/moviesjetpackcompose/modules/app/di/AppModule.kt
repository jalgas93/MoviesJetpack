package com.example.moviesjetpackcompose.modules.app.di

import com.example.moviesjetpackcompose.http.ApiClient
import com.example.moviesjetpackcompose.modules.app.helpers.Config
import com.example.moviesjetpackcompose.modules.popular.PopularRepository
import com.example.moviesjetpackcompose.modules.popular.PopularRepositoryImpl
import com.example.moviesjetpackcompose.modules.popular.intent.PopularViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideProductionNewsService(): ApiClient {
        val retrofit = Retrofit.Builder()
            .baseUrl(Config.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun providePopularRepository(client: ApiClient): PopularRepository {
        return PopularRepositoryImpl(client)
    }
//    @Provides
//    @Singleton
//    fun provideViewModel(repository: PopularRepository): PopularViewModel {
//        return PopularViewModel(repository)
//    }
}