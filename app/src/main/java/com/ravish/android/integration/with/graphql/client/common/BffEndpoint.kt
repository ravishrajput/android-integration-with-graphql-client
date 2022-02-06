package com.ravish.android.integration.with.graphql.client.common

sealed class BffEndpoint : Endpoint {

    object Dev : BffEndpoint() {
        override val baseUrl: String
            get() = "http://10.0.2.2:8080/graphql"
    }

    object Prod : BffEndpoint() {
        override val baseUrl: String
            get() = "http://api.ravishrajput.com/bff/graphql"
    }
}