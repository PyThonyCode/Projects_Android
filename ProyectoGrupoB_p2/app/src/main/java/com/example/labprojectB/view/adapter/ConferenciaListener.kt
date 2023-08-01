package com.example.labprojectB.view.adapter

import com.example.labprojectB.Model.Conferencia

interface ConferenciaListener {
    fun onConferenciaClicked(conferencia: Conferencia, position: Int)
}