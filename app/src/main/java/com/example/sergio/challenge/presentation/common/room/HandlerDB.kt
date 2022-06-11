package com.example.sergio.challenge.presentation.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sergio.challenge.data.list.source.local.CategoriesDao
import com.example.sergio.challenge.data.list.source.local.ProductosDao
import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.Categories
import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.*

@Database(entities = [Categories::class, Results::class], version = 1)
@TypeConverters(Converters::class)
abstract class HandlerDB: RoomDatabase() {

    abstract fun categoriesDAO(): CategoriesDao
    abstract fun productosDao(): ProductosDao

    companion object {
            @Volatile
        private var INSTANCE: HandlerDB?= null

        fun getInstance(context: Context): HandlerDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HandlerDB::class.java,
                        "challenge-db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}