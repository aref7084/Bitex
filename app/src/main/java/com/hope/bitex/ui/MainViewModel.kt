package com.hope.bitex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hope.bitex.data.ResponseModel
import com.hope.bitex.data.model.BuyResponseModel
import com.hope.bitex.data.repository.TradeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TradeRepository,
) : ViewModel() {

    var tradResponse = MutableLiveData<ResponseModel<BuyResponseModel>>()

    fun trade(token:String,amount: String) {
        viewModelScope.launch {
            repository.trade(token, amount).collect {
                tradResponse.postValue(it)
            }
        }
    }



}