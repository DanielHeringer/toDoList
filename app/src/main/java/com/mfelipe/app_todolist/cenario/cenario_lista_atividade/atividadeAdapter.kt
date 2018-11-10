package com.mfelipe.app_todolist.cenario.cenario_lista_atividade


import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.mfelipe.app_todolist.entidade.Atividade
import com.mfelipe.app_todolist.R
import kotlinx.android.synthetic.main.atividade_item_lista.view.*

class atividadeAdapter(val atividades: List<Atividade>)
    : RecyclerView.Adapter<atividadeAdapter.ViewHolder>() {

    //salva a funçao do clique no item
    var clickListener: ((Index: Int) -> Unit)? = null
    //sava a funçaõ do cique longo do item
    var cliqueLongoListener: ((Index: Int) -> Boolean)? = null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.atividade_item_lista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return atividades.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(atividades[position], clickListener, cliqueLongoListener)
    }
    //configuração e funçao de clique em cada item da lista
    fun setOnItemClickListener(clique: ((Index: Int) -> Unit)){
        this.clickListener = clique
    }

    fun configuraCLiqueLongo(cliqueLongo: ((Index: Int) -> Boolean)){
        this.cliqueLongoListener = cliqueLongo
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(atividade: Atividade, clickListener: ((Index: Int) -> Unit)?,
                     cliqueLongoListener: ((Index: Int) -> Boolean)?) {
            itemView.tvNome.text = atividade.nome
            itemView.tvData.text = atividade.data

            if(clickListener != null){
                itemView.setOnClickListener(){
                    clickListener.invoke(adapterPosition)
                }

            }
            if (cliqueLongoListener != null){
                itemView.setOnLongClickListener(){
                    cliqueLongoListener.invoke(adapterPosition)
                }
            }
        }

    }
}