package data.network

sealed class ApiDefinition {
    sealed class ApiEndpoint(val path: String) {
        data object Login : ApiEndpoint(path = "auth/login")
        data object Products : ApiEndpoint(path = "products")
    }

    object ApiField {

        const val PARAM_LIMIT = "limit"
        const val PARAM_SKIP = "skip"
    }
}
