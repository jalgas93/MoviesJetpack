package com.example.moviesjetpackcompose.modules.app.di

import com.example.moviesjetpackcompose.http.ApiClient
import com.example.moviesjetpackcompose.modules.popular.PopularRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) // this is new
object RepositoryModule{

    @Provides
    @ViewModelScoped // this is new
    fun providesRepo(client: ApiClient): PopularRepositoryImpl { // this is just fake repository
        return PopularRepositoryImpl(client)
    }
}