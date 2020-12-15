package com.cristhianbonilla.revistaprioridades.platform.devices

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.pm.PackageManager
import android.os.Build
import com.cristhianbonilla.data.source.platform.DevicePlatformSource
import com.cristhianbonilla.domain.model.device.DeviceModelInfo


class HandlerDevice(private val context: Context) : DevicePlatformSource {

    companion object {
        private const val DEVICE_SHARED_PREFERENCES = "DEVICE_SHARED_PREFERENCES"
        private const val IS_TRUSTED_KEY = "IS_TRUSTED_KEY"
    }

    override fun getName(): String = Build.MODEL

    override fun getAppVersion(): String =
        try {
            context.packageManager.getPackageInfo(context.packageName, 0)
                .versionName
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }

    override fun getOSVersion(): String = Build.VERSION.RELEASE

    override fun getUUID(): String = "5cdbaab7-08dc-410b-b65c-ff1a4016514e"

    override fun isKeyguardSecure(): Boolean = true

    override fun isRooted(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime()
                .exec("su")
            true
        } catch (e: Exception) {
            false
        } finally {
            try {
                process?.let { it.destroy() }
            } catch (e: Exception) {
                //ignored
            }
        }
    }

    override fun getDeviceInfo() = DeviceModelInfo(
        getUUID(), getName(), getOSVersion(), "ANDROID", isRooted(), getAppVersion()
    )

    override fun isTrusted(): Boolean =
        context.getSharedPreferences(DEVICE_SHARED_PREFERENCES, MODE_PRIVATE)
            .getBoolean(IS_TRUSTED_KEY, false)

    override fun setTrusted(trusted: Boolean) {
        context.getSharedPreferences(DEVICE_SHARED_PREFERENCES, MODE_PRIVATE)
            .edit()
            .putBoolean(IS_TRUSTED_KEY, trusted)
            .apply()
    }
}