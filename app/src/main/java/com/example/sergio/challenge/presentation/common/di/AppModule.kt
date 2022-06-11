package com.example.sergio.challenge.presentation.common.di

import android.app.Application
import com.example.sergio.challenge.presentation.common.retrofit.WebServiceHandler
import com.example.sergio.challenge.presentation.common.room.HandlerDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun webServiceHandlerProvider(app: Application) = WebServiceHandler.getInstance(app)

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = HandlerDB.getInstance(app)

    @Provides
    @Singleton
    fun categotiesDaoProvider(app : Application) = HandlerDB.getInstance(app).categoriesDAO()

    @Provides
    @Singleton
    fun productosDaoProvider(app : Application) = HandlerDB.getInstance(app).productosDao()
}