package com.oleg.fashionclothes.db.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.oleg.fashionclothes.db.room.common.Converters

/**
 * Created by oleg on 14.03.2018.
 */
@Entity(tableName = "productData")
@TypeConverters(Converters::class)
data class Product(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "type") var type:String?,
        @ColumnInfo(name = "available")var available:String?,
        @ColumnInfo(name = "selling_type")var selling_type:String?,
        @ColumnInfo(name = "name")var name:String?,
        @ColumnInfo(name = "description")var description:String?,
        @ColumnInfo(name = "currencyId")var currencyId:String?,
        @ColumnInfo(name = "categoryId")var categoryId:String?,
        @ColumnInfo(name = "price")var price:String?,
        @ColumnInfo(name = "picture")var picture:List<String>?,
        @ColumnInfo(name = "model")var model:String?,
        @ColumnInfo(name = "color")var color:String?
)