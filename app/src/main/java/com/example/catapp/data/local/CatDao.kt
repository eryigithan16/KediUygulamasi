package com.example.catapp.data.local

import androidx.room.*
import com.example.catapp.common.ImageTypeConverter
import com.example.catapp.data.model.Cat

@Dao
@TypeConverters(ImageTypeConverter::class)
interface CatDao {

    @Insert
    suspend fun insertAll(vararg cats: Cat) : List<Long>
    //bir tane de insertFavCats yazmalı mıyım bunu denicem hata alırsam

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavorites(cat : Cat)

    @Query("SELECT * FROM cat")
    suspend fun getAllCats() : List<Cat>

    @Query("SELECT * FROM cat WHERE catIsFavorited = 1")
    suspend fun getFavCats() : List<Cat>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCat(cat : Cat)

    @Query("SELECT * FROM cat WHERE catId= :catId")
    suspend fun getCat(catId : Int) : Cat

    //bir tane de getFavCat yazmalı mıyım? , hata alırsam bunu denicem

    @Query("DELETE FROM cat WHERE catId = :catId")
    suspend fun deleteFavCat(catId : Int)

}