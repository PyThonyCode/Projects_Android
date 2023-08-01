package com.example.labprojectB.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.labprojectB.Model.Ponentes
import com.example.labprojectB.network.Callback
import com.example.labprojectB.network.FirestoreService
import java.lang.Exception

class PonentesViewModel {
    val firebaseservice=FirestoreService()
    val listadoponentes:MutableLiveData<List<Ponentes>?> = MutableLiveData()
    val isloading=MutableLiveData<Boolean>()
    
    fun refresh() {
        getPonentesfromFirebase()
    }

    private fun getPonentesfromFirebase() {
        firebaseservice.getPonentes(object: Callback<List<Ponentes>> {
            override fun onSuccess(result: List<Ponentes>?) {
                listadoponentes.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }

        })
    }

    private fun processFinished() {
        isloading.value=true
    }
}