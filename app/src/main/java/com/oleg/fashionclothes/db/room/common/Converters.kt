package com.oleg.fashionclothes.db.room.common

import android.arch.persistence.room.TypeConverter




/**
 * Created by oleg on 14.03.2018.
 */
class Converters {


    @TypeConverter
    fun fromPicture(picture: List<String>): String?{
        return picture.joinToString(separator = ",",prefix = "",postfix = "")
    }

    @TypeConverter
    fun toPicture(data: String): List<String>? {
        return data.split(",")
    }
}