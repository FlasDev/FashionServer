package com.oleg.fashionclothes.di.model

import android.app.Application
import android.arch.persistence.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.oleg.fashionclothes.db.room.ProductDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by oleg on 13.03.2018.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): ProductDatabase{
        return Room.databaseBuilder(application,ProductDatabase::class.java,"fashiondatabase").build()
    }

    @Singleton
    @Provides
    fun provideFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}