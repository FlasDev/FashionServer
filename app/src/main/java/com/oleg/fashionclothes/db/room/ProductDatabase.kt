package com.oleg.fashionclothes.db.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by oleg on 14.03.2018.
 */
@Database(entities = [Product::class],version = 1)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}