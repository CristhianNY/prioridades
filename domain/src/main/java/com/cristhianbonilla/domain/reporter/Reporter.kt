package com.cristhianbonilla.domain.reporter

import com.cristhianbonilla.domain.reporter.ReporterLogSeverityLevel.INFO
import com.cristhianbonilla.domain.reporter.ReporterLogSeverityLevel.WARNING

interface Reporter {

    companion object {
        const val CLIENT_ID_KEY = "UserId"
        const val VERSION_NAME_KEY = "versionName"
        const val VERSION_CODE_KEY = "versionCode"
    }

    fun initialize(initializer: ReporterInitializer)

    fun log(
        key: String,
        value: String,
        logSeverityLevel: ReporterLogSeverityLevel = INFO
    )

    fun exception(
        exception: Exception,
        logSeverityLevel: ReporterLogSeverityLevel = WARNING
    )

    fun recordBreadcrumb(breadCrumb: ReporterBreadCrumb)

    fun setUserData(userData: ReporterUserData)

}

data class ReporterInitializer(
    val versionCode: Int,
    val versionName: String
)

data class ReporterBreadCrumb(
    val screenName: String
)

data class ReporterUserData(
    val userId: String
)

enum class ReporterLogSeverityLevel {
    INFO,
    WARNING,
    CRITICAL
}