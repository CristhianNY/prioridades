package com.cristhianbonilla.domain.exception

sealed class Failure {
    object NetworkConnection : Failure()
    class RemoteError(
        val type: RemoteTypeError = RemoteTypeError.GENERIC,
        val description: String? = null
    ) : Failure()

    object LocalError : Failure()
    object SessionExpired : Failure()
    object SubscriptionNotActivated : Failure()
    class InvalidRequestParam(val description: String?) : Failure()
    sealed class SecurityError : Failure() {
        object NotSupportedError : SecurityError()
        object KeyPermanentlyInvalidatedError : SecurityError()
        object UnknownError : SecurityError()
    }

    abstract class FeatureFailure : Failure()
}