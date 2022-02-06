package com.ravish.android.integration.with.graphql.client

interface ApiRepository {
    suspend fun getAllFlights(): List<Flight>
}