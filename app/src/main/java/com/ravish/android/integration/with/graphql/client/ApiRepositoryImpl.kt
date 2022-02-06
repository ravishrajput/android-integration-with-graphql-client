package com.ravish.android.integration.with.graphql.client

import com.apollographql.apollo3.ApolloClient

class ApiRepositoryImpl(
    private val apolloClient: ApolloClient
) : ApiRepository {

    override suspend fun getAllFlights(): List<Flight> {
        val result = apolloClient.query(
            GetAllFlightsQuery()
        ).execute().dataAssertNoErrors
        return result.getAllFlights?.map {
            Flight(
                it?.from.orEmpty(),
                it?.to.orEmpty(),
                it?.departure.orEmpty(),
                it?.arrival.orEmpty(),
                it?.airlinesName.orEmpty()
            )
        } ?: emptyList()
    }
}