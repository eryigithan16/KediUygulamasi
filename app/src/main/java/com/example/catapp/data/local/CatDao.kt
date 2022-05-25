package com.example.catapp.data.local

import androidx.room.*
import com.example.catapp.common.ImageTypeConverter
import com.example.catapp.data.model.Cat

@Dao
@TypeConverters(ImageTypeConverter::class)
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavorites(cat : Cat)

    @Query("SELECT * FROM cat")
    suspend fun getAllCats() : List<Cat>

    @Query("SELECT * FROM cat WHERE catId= :catId")
    suspend fun getCat(catId : Int) : Cat

    @Query("DELETE FROM cat WHERE cat.name = :catName")
    suspend fun deleteFavCat(catName : String) //bunu catıd yerine name yap

}