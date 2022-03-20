package com.cemreonur.daggerhiltyt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RetrofitRepository) : ViewModel() {

    var liveData: MutableLiveData<List<Post>>

    init {
        liveData = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Post>> {
        return liveData
    }

    fun loadData() {
        repository.getPosts(liveData)
    }
}