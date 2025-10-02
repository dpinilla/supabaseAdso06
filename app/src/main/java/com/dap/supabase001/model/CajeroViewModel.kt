package com.dap.supabase001.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CajeroViewModel: ViewModel() {
    // Create a LiveData with a String
    val currentName: MutableLiveData<MutableList<ModelCajero>> by lazy {
        MutableLiveData<MutableList<ModelCajero>>()
    }

    fun addAllCajero(datos: List<ModelCajero>){
        val list = currentName.value?:mutableListOf()
        list.clear()
        list.addAll(datos)
        currentName.value = list
    }

    fun addCajero(datos: ModelCajero){
        val list = currentName.value?:mutableListOf()
        list.add(datos)
        currentName.value = list
    }
}