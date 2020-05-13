package com.utmcat.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.utmcat.data.repository.Repository
import com.utmcat.utils.Resursa
import kotlinx.coroutines.Dispatchers

class CatViewModel(private val repository: Repository) : ViewModel() {

    fun getNote() = liveData(Dispatchers.IO) {
        emit(Resursa.loading(data = null))
        try {
            emit(Resursa.success(data = repository.getStudenti()))
        } catch (exception: Exception) {
            emit(Resursa.error(data = null, message = exception.message ?: "Eroare de conectare!"))
        }
    }
}