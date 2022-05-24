package com.example.catapp.di

import com.example.catapp.api.CatsRemoteApi
import com.example.catapp.common.Constants
import com.example.catapp.data.remote.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun repoProvider(catRemoteDataSource: CatRemoteDataSource) : CatRepository {
        return CatRepositoryImpl(catRemoteDataSource)
    }

    @Singleton
    @Provides
    fun dataSourceProvider(catsRemoteApi: CatsRemoteApi) : CatRemoteDataSource{
        return CatRemoteDataSourceImpl(catsRemoteApi)
    }

    @Singleton
    @Provides
    fun retrofitInstanceProvider() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun remoteApiProvider(retrofit: Retrofit) : CatsRemoteApi{
        return retrofit.create(CatsRemoteApi::class.java)
    }
}