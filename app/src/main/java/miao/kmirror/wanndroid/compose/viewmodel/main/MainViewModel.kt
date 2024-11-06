package miao.kmirror.wanndroid.compose.viewmodel.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import miao.kmirror.wanndroid.compose.network.WanAndroidApi
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mWanAndroidApi: WanAndroidApi
    fun initData() {
    }
}