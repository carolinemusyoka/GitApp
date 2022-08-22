package com.carolmusyoka.gitapp.di

import android.app.Application
import com.carolmusyoka.gitapp.BuildConfig
import com.carolmusyoka.gitapp.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    @Provides
    @Singleton
    fun providesOkhttpClient(cache: Cache): OkHttpClient{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) okHttpClient.addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val BASE_URL = "https://api.github.com/"
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
                //skip this
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    private val apiInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val url = originalHttpUrl.newBuilder()
//            .addQueryParameter("client_id", BuildConfig.CLIENT_ID)
//            .addQueryParameter("client_secret", BuildConfig.CLIENT_SECRET)
            .build()
        request.url(url)
        chain.proceed(request.build())
    }
    private val cacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(30, TimeUnit.DAYS)
            .build()
        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

    @Provides
    @Singleton
    fun providesCache(app: Application): Cache{
        val CACHE_NAME = "gitapp-cache"
        return Cache(
            File(app.applicationContext.cacheDir, CACHE_NAME),
            10485760L
        )
    }


}