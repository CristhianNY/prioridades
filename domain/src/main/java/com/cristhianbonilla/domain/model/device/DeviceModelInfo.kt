package com.cristhianbonilla.domain.model.device

data class DeviceModelInfo(
    val uuid: String,
    val device: String,
    val osVersion: String,
    val platform: String,
    val rooted: Boolean,
    val version: String
)