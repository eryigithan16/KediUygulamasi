package com.example.catapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.catapp.common.ImageTypeConverter
import com.example.catapp.data.model.Cat

@Database(entities = arrayOf(Cat::class), version = 1)
@TypeConverters(ImageTypeConverter::class)

abstract class CatDatabase : RoomDatabase(){

    abstract fun catDao() : CatDao

}