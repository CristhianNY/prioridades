package com.cristhianbonilla.data.source.platform

import com.cristhianbonilla.domain.model.device.DeviceModelInfo

interface DevicePlatformSource {
    fun getName(): String

    fun getAppVersion(): String

    fun getOSVersion(): String

    fun getUUID(): String

    fun isKeyguardSecure(): Boolean

    fun isRooted(): Boolean

    fun getDeviceInfo(): DeviceModelInfo

    fun isTrusted(): Boolean

    fun setTrusted(trusted: Boolean)
}