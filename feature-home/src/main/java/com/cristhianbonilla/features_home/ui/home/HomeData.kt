package com.cristhianbonilla.features_home.ui.home

import com.cristhianbonilla.foundations.base.BaseData

import com.cristhianbonilla.foundations.livedata.MyLiveData
import com.cristhianbonilla.features_home.ui.home.HomeMagazineState.Loading
import com.cristhianbonilla.features_home.ui.home.HomeMagazineState.Error
import com.cristhianbonilla.features_home.ui.home.HomeMagazineState.Success

class HomeData(
var magazineList: MyLiveData<List<String>> = MyLiveData(mutableListOf()),
var keyWordList: MyLiveData<List<String>> = MyLiveData(mutableListOf())
) : BaseData<HomeMagazineState>(){

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


    fun submitMagazineList(magazine:List<String>){
        magazineList update magazine
    }

    fun submitKeyWords(keyWords:List<String>){
        keyWordList update keyWords
    }

}
