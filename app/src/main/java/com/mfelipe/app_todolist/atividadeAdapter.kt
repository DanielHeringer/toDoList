package com.mfelipe.app_todolist


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.atividade_item_lista.view.*

class atividadeAdapter(val atividades: List<String>)
    : RecyclerView.Adapter<atividadeAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.atividade_item_lista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return atividades.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(atividades[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(atividadeNome: String) {
            itemView.tvNome.text = atividadeNome
        }

    }
}