package com.example.labprojectB.view.adapter

import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labprojectB.Model.Conferencia
import com.example.labprojectB.R
import java.text.SimpleDateFormat

class ConferenciaAdapter (val ConferenciaListener: ConferenciaListener) : RecyclerView.Adapter<ConferenciaAdapter.ViewHolder>() {
    val listConference = ArrayList<Conferencia>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_calendario,parent,false))

    override fun getItemCount()=listConference.size

    override fun onBindViewHolder(holder: ConferenciaAdapter.ViewHolder, position: Int) {
        val conferencia = listConference[position] as Conferencia
        holder.tvnombreconferencia.text = conferencia.titulo
        holder.tvponenteconferencia.text = conferencia.ponente
        holder.tvtopicoconferencia.text = conferencia.topico

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val calendario = Calendar.getInstance()
        calendario.time = conferencia.datetime
        val horaformato = simpleDateFormatAMPM.format(conferencia.datetime)

        holder.tvhoraconferencia.text = horaformato
        holder.tvAMPMconferencia.text = simpleDateFormatAMPM.format(conferencia.datetime).toUpperCase()
    }

    fun updatedat(data: List<Conferencia>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvnombreconferencia = itemView.findViewById<TextView>(R.id.TVTema)
        val tvponenteconferencia = itemView.findViewById<TextView>(R.id.TVExpositor)
        val tvtopicoconferencia = itemView.findViewById<TextView>(R.id.TVTopico)
        val tvhoraconferencia = itemView.findViewById<TextView>(R.id.TVHora)
        val tvAMPMconferencia = itemView.findViewById<TextView>(R.id.TVAMPM)
    }

}