package com.mfelipe.app_todolist.cenario.cenario_salva_atividade

import android.content.Context
import com.mfelipe.app_todolist.entidade.Atividade

interface salvaAtividadeContract {

    interface View {
        fun salvoComSucesso()
    }

    interface Presenter {
        fun onSalvaAtividade(context: Context, atividade: Atividade)
    }
}