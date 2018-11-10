package com.mfelipe.app_todolist

import android.content.Context

interface ListaCoisasContract {

    interface View{
        fun exibeLista(lista: MutableList<Atividade>)
    }

    interface Presenter{
        fun onAtualizaLista(context: Context)
        fun onDeleteAtividade(context: Context, atividade: Atividade)
    }
}