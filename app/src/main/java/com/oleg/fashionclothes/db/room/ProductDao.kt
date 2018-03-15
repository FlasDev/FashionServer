package com.oleg.fashionclothes.db.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by oleg on 14.03.2018.
 */
@Dao
interface ProductDao{
    @Query("SELECT * from productData")
    fun getAll(): Flowable<List<Product>>

    @Insert(onConflict = REPLACE)
    fun insert(product: Product)

    @Insert(onConflict = REPLACE)
    fun insertList(product: Product?)

    @Delete
    fun deleteAll(product: List<Product>)
}