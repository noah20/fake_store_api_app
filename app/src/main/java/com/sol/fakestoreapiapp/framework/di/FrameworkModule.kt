package com.sol.fakestoreapiapp.framework.di

import com.sol.fakestoreapiapp.framework.remote.interfaces.FakeStoreRepository
import com.sol.fakestoreapiapp.framework.remote.services.NetworkService
import com.sol.fakestoreapiapp.framework.repo.FakeStoreRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {

    @Singleton
    @Provides
    fun provideFakeStoreRepository(remote: NetworkService) : FakeStoreRepository {

        return FakeStoreRepositoryImp(remote)

    }

}