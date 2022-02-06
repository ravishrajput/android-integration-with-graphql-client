package com.ravish.android.integration.with.graphql.client.di

import com.apollographql.apollo3.ApolloClient
import com.ravish.android.integration.with.graphql.client.ApiRepository
import com.ravish.android.integration.with.graphql.client.ApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideAppRepoModule(
        apolloClient: ApolloClient
    ): ApiRepository = ApiRepositoryImpl(apolloClient)
}