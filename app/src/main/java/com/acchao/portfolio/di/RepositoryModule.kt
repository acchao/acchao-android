package com.acchao.portfolio.di

import android.content.Context
import com.acchao.portfolio.data.PortfolioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providePortfolioRepository(@ApplicationContext appContext: Context): PortfolioRepository {
        return PortfolioRepository(appContext)
    }
}