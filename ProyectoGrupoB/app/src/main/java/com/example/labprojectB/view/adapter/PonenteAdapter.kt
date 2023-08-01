package com.example.labprojectB.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.labprojectB.Model.Ponentes
import com.example.labprojectB.R

class PonenteAdapter: RecyclerView.Adapter<PonenteAdapter.ViewHolder>(){

    val listPonent=ArrayList<Ponentes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_expositores, parent, false))

    override fun getItemCount()=listPonent.size

    override fun onBindViewHolder(holder: PonenteAdapter.ViewHolder, position: Int) {
        val ponente = listPonent[position] as Ponentes
        holder.tvnombreponente.text = ponente.nombre
        holder.tvtrabajoponente.text = ponente.trabajo

        Glide.with(holder.itemView.context)
            .load(ponente.imagen)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivimagenponente)
    }

    class ViewHolder (ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val tvnombreponente = itemView.findViewById<TextView>(R.id.TVNombre)
        val tvtrabajoponente = itemView.findViewById<TextView>(R.id.TVTrabajo)
        val ivimagenponente = itemView.findViewById<ImageView>(R.id.TVimage)
    }

}