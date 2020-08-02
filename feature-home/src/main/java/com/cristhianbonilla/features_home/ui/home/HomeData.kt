package com.cristhianbonilla.features_home.ui.home

import com.cristhianbonilla.features_home.ui.home.HomeMagazineState.*
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData
import java.util.*
import kotlin.collections.ArrayList

class HomeData(
    var magazineList: MyLiveData<List<String>> = MyLiveData(mutableListOf()),
    var keyWordList: MyLiveData<List<String>> = MyLiveData(mutableListOf()),
    var lastYears: MyLiveData<List<String>> = MyLiveData(mutableListOf())

    ) : BaseData<HomeMagazineState>() {

    override fun loading() {
        super.loading()
        this updateState Loading
    }

    override fun error() {
        super.error()
        this updateState Error
    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
        updateState(Success)
    }


    fun submitMagazineList(magazine: List<String>) {
        magazineList update magazine
    }

    fun submitKeyWords(keyWords: List<String>) {
        keyWordList update keyWords
    }

    fun submitLastFourYear() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years: ArrayList<String> = ArrayList()
        years.add(currentYear.toString())
        years.add((currentYear - 1).toString())
        years.add((currentYear - 2).toString())
        years.add((currentYear - 3).toString())
        lastYears update years
    }

}
