package com.mfelipe.app_todolist

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
data class Atividade(var nome: String,
                     var data: String?= null,
                     @PrimaryKey (autoGenerate = true)
                     var id: Int = 0) : Serializable