package com.example.lab2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kbtu_lab2_network.network.ApiFactory
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>> = _cats

    private val _request = MutableLiveData<Request>()
    val request: LiveData<Request>
        get() = _request


    init {
        initRequest()
    }

    fun getCats() {
        request.value?.let { currentRequest ->
            viewModelScope.launch {
                try {
                    val response = ApiFactory.apiService.getCats(
                        name = currentRequest.name,
                        minWeight = currentRequest.minWeight,
                        maxWeight = currentRequest.maxWeight,
                        minLifeExpectancy = currentRequest.minLifeExpectancy,
                        maxLifeExpectancy = currentRequest.maxLifeExpectancy,
                        shedding = currentRequest.shedding,
                        familyFriendly = currentRequest.familyFriendly,
                        playfulness = currentRequest.playfulness,
                        grooming = currentRequest.grooming,
                        otherPetsFriendly = currentRequest.otherPetsFriendly,
                        childrenFriendly = currentRequest.childrenFriendly,
                        offset = currentRequest.offset
                    )
                    _cats.value = response.map { it.toCat() }
                } catch (e: Exception) {

                }
            }
        }
    }

    fun initRequest() {
        _request.value = Request()
    }

    fun updateRequest(request: Request){
        _request.value=request
    }

    fun clearCats() {
        _cats.value = emptyList()
    }


}

