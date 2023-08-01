package com.example.labprojectB.network

import com.example.labprojectB.Model.Conferencia
import com.example.labprojectB.Model.Ponentes
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val PONENTES_COLLECTION_NAME="ponentess"
const val CONFERENCIAS_COLLECTION_NAME="conferenciass"

class FirestoreService {
    val firestoreFirebase = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init{
        firestoreFirebase.firestoreSettings=settings
    }

    fun getPonentes(callback: Callback<List<Ponentes>>){
        firestoreFirebase.collection(PONENTES_COLLECTION_NAME)
            .orderBy("categoria")
            .get()
            .addOnSuccessListener { result->
                for (doc in result)
                {
                    val list= result.toObjects(Ponentes::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getConferencias(callback: Callback<List<Conferencia>>){
        firestoreFirebase.collection(CONFERENCIAS_COLLECTION_NAME)
            .orderBy("categoria")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result)
                {
                    val list= result.toObjects(Conferencia::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}