package com.mfelipe.app_todolist

import java.io.Serializable

data class Atividade(val nome: String,
                     val data: String?= null) : Serializable