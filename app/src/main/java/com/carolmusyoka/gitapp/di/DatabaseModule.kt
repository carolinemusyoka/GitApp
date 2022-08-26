package com.carolmusyoka.gitapp.di

import android.content.Context
import androidx.room.Room
import com.carolmusyoka.gitapp.data.local.AppDatabase
import com.carolmusyoka.gitapp.data.local.BookmarksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideDao(appDatabase: AppDatabase): BookmarksDao{
        return appDatabase.bookmarksDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "git_app_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context{
        return appContext
    }

    @Provides
    @Singleton
    fun getCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
}