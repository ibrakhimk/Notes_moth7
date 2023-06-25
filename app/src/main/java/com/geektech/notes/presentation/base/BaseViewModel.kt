package com.geektech.notes.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geektech.notes.domain.utils.Resource
import com.geektech.notes.presentation.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<com.geektech.notes.domain.utils.Resource<T>>.collectData(
        _state: MutableStateFlow<UIState<T>>
    ) {
        viewModelScope.launch {
            this@collectData.collect { res ->
                when (res) {
                    is com.geektech.notes.domain.utils.Resource.Error -> {
                        _state.value = UIState.Error(res.message!!)
                    }

                    is com.geektech.notes.domain.utils.Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }

                    is com.geektech.notes.domain.utils.Resource.Success -> {
                        if (res.data != null) {
                            _state.value = UIState.Success(res.data!!)
                        }
                    }
                }
            }
        }
    }
}