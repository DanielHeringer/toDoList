package com.mfelipe.app_todolist

import android.content.Context
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListaCoisasPresenter(val view: ListaCoisasContract.View): ListaCoisasContract.Presenter{

    override fun onAtualizaLista(context: Context){

        val atividadeDao = AppDatabase.getIstance(context).atividadeDao()
        doAsync {
            val listaAtividades = atividadeDao.getAll() as MutableList<Atividade>
            uiThread {
                view.exibeLista(listaAtividades)
            }
        }

    }

   override fun onDeleteAtividade(context: Context, atividade: Atividade){

        val atividadeDao = AppDatabase.getIstance(context).atividadeDao()
        doAsync {
            atividadeDao.delete(atividade)

            uiThread {
                onAtualizaLista(context)
            }
        }
    }
}