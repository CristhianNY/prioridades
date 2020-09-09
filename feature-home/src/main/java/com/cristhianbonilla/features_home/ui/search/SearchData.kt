package com.cristhianbonilla.features_home.ui.search

import com.cristhianbonilla.features_home.ui.perfil.ProfileState
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData

class SearchData(
    val year: MyLiveData<String> = MyLiveData(""),
    val keyWord: MyLiveData<String> = MyLiveData("")
) : BaseData<SearchState>() {
}