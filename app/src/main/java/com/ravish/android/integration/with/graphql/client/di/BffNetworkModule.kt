package com.ravish.android.integration.with.graphql.client.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.ravish.android.integration.with.graphql.client.BuildConfig
import com.ravish.android.integration.with.graphql.client.common.BffEndpoint
import com.ravish.android.integration.with.graphql.client.common.Endpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class BffNetworkModule {

    @Provides
    fun provideApolloClient(
        @Named("bffEndpoint") bffEndpoint: Endpoint,
        @Named("bffOkHttp") okHttpClient: OkHttpClient
    ): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(bffEndpoint.baseUrl)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Named("bffOkHttp")
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        addInterceptor {
            it.proceed(it.request().newBuilder().apply {
                header("User-Agent", "AndroidGraphQLApp")
                header("Accept", "application/json")
            }.build())
        }
    }.build()

    @Provides
    @Named("bffEndpoint")
    fun provideBffEndPoint(): Endpoint {
        return when (BuildConfig.BUILD_TYPE) {
            "debug" -> BffEndpoint.Dev
            else -> BffEndpoint.Prod
        }
    }

    private class BffAuthorizationInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic kvjnbvkgrmb=")
                .build()
            return chain.proceed(request)
        }
    }

    companion object {
        private const val TIMEOUT = 30L
    }
}