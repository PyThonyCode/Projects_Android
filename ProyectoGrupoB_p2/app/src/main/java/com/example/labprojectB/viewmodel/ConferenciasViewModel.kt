package com.example.labprojectB.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.labprojectB.Model.Conferencia
import com.example.labprojectB.network.Callback
import com.example.labprojectB.network.FirestoreService
import java.lang.Exception

class ConferenciasViewModel: ViewModel() {
    val firebaseservice= FirestoreService()
    val listadoconferencias: MutableLiveData<List<Conferencia>> = MutableLiveData()
    val isloading= MutableLiveData<Boolean>()

    fun refresh() {
        getConferenciasfromFirebase()
    }

    private fun getConferenciasfromFirebase() {
        firebaseservice.getConferencias(object: Callback<List<Conferencia>> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onSuccess(result: List<Conferencia>?) {
                listadoconferencias.postValue(result)
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