package com.oleg.fashionclothes.db.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oleg on 14.03.2018.
 */
@Entity(tableName = "productData")
data class Product(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "type") var type:String,
    @ColumnInfo(name = "available")var available:String,
    @ColumnInfo(name = "selling_type")var selling_type:String,
    @ColumnInfo(name = "name")var name:String,
    @ColumnInfo(name = "description")var description:String,
    @ColumnInfo(name = "currencyId")var currencyId:String,
    @ColumnInfo(name = "categoryId")var categoryId:String,
    @ColumnInfo(name = "price")var price:String,
    @ColumnInfo(name = "picture")var picture:List<String>,
    @ColumnInfo(name = "model")var model:String,
    @ColumnInfo(name = "color")var color:String,
    @ColumnInfo(name = "param")var param:String
)