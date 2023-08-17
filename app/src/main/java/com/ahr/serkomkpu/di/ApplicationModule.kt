package com.ahr.serkomkpu.di

import android.content.Context
import androidx.room.Room
import com.ahr.serkomkpu.data.ElectorateRepositoryImpl
import com.ahr.serkomkpu.data.local.SerkomKpuDatabase
import com.ahr.serkomkpu.domain.ElectorateRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideKpuDatabase(
        @ApplicationContext context: Context
    ): SerkomKpuDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = SerkomKpuDatabase::class.java,
            name = SerkomKpuDatabase.DATABASE_NAME
        ).build()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindElectorateRepository(
        electorateRepositoryImpl: ElectorateRepositoryImpl
    ): ElectorateRepository

}