package com.carolmusyoka.gitapp.di

import com.carolmusyoka.gitapp.data.repository.MainRepository
import com.carolmusyoka.gitapp.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

}