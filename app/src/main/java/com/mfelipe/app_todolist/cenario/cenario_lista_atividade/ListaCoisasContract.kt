package com.mfelipe.app_todolist.cenario.cenario_lista_atividade

import android.content.Context
import com.mfelipe.app_todolist.entidade.Atividade

interface ListaCoisasContract {

    interface View{
        fun exibeLista(lista: MutableList<Atividade>)
    }

    interface Presenter{
        fun onAtualizaLista(context: Context)
        fun onDeleteAtividade(context: Context, atividade: Atividade)
    }
}