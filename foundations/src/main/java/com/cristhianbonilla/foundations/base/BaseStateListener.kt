package com.cristhianbonilla.foundations.base

interface BaseStateListener<S : BaseState> {
    fun onStateChanged(state: S)
}