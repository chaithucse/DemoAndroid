package com.example.demoandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demoandroid.BuildConfig
import com.example.demoandroid.data.DataRepository
import com.example.demoandroid.data.model.RelatedTopic
import com.example.demoandroid.data.model.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CharViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {
    private val selected = MutableLiveData<RelatedTopic>()

    val charList: LiveData<UserResponse> = liveData(Dispatchers.Main) {
        val data = repository.getDataList(BuildConfig.api_type)
        emit(data)
    }

    fun select(item: RelatedTopic) {
        selected.value = item
    }

    fun getSelected(): LiveData<RelatedTopic> {
        return selected
    }
}