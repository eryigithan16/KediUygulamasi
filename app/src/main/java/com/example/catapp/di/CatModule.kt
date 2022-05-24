package com.example.catapp.di

import android.content.Context
import androidx.room.Room
import com.example.catapp.api.CatsRemoteApi
import com.example.catapp.common.Constants
import com.example.catapp.common.ImageTypeConverter
import com.example.catapp.data.local.*
import com.example.catapp.data.remote.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CatModule {
    @Singleton
    @Provides
    fun remoteRepoProvider(catRemoteDataSource: CatRemoteDataSource) : CatRepository {
        return CatRepositoryImpl(catRemoteDataSource)
    }

    @Singleton
    @Provides
    fun remoteDataSourceProvider(catsRemoteApi: CatsRemoteApi, catLocalRepository: CatLocalRepository) : CatRemoteDataSource{
        return CatRemoteDataSourceImpl(catsRemoteApi,catLocalRepository)
    }

    @Singleton
    @Provides
    fun localDataSourceProvider(catDao: CatDao) :CatLocalDataSource {
        return CatLocalDataSourceImpl(catDao)
    }

    @Singleton
    @Provides
    fun localRepoProvider(catLocalDataSource: CatLocalDataSource) : CatLocalRepository{
        return CatLocalRepositoryImpl(catLocalDataSource)
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

    @Singleton
    @Provides
    fun databaseProvider(
        @ApplicationContext app:Context
    ) = Room.databaseBuilder(app,CatDatabase::class.java,"cat_db")
        .addTypeConverter(ImageTypeConverter())
        .build()

    @Singleton
    @Provides
    fun daoProvider(database: CatDatabase) = database.catDao()

}