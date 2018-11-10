package com.mfelipe.app_todolist.cenario.cenario_salva_atividade

import android.content.Context
import com.mfelipe.app_todolist.database.AppDatabase
import com.mfelipe.app_todolist.database.AtividadeDao
import com.mfelipe.app_todolist.entidade.Atividade
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class salvaAtividadePresenter(val view: salvaAtividadeContract.View): salvaAtividadeContract.Presenter {

    override fun onSalvaAtividade(context: Context, atividade: Atividade){
        val atividDao: AtividadeDao = AppDatabase.getIstance(context).atividadeDao()
        doAsync {
            atividDao.insert(atividade)

            uiThread {
                view.salvoComSucesso()
            }
        }
    }
}