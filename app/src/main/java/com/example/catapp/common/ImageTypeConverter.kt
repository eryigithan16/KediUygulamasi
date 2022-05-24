package com.example.catapp.common

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.catapp.data.model.CatImage
import com.google.gson.Gson

@ProvidedTypeConverter
class ImageTypeConverter {
    @TypeConverter
    fun fromImage(image: CatImage?) : String{
        //return image!!.url
        if (image != null) {
            return Gson().toJson(image.url)
        } else{
            return ""
        }
    }

    @TypeConverter
    fun toImage(image: String?) : CatImage {
        return image?.let {
            CatImage(it.replace("\"", ""))
        }!!
    }

}