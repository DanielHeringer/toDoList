package com.mfelipe.app_todolist

import android.arch.persistence.room.*

@Dao
interface AtividadeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(atividade: Atividade)

    @Query("SELECT * FROM atividade")
    fun getAll(): List<Atividade>

    //@Update
    //fun uptade(atividade: Atividade)

    @Delete
    fun delete(atividade: Atividade)

    //Buscar uma atividade especifica
    @Query("SELECT * FROM atividade WHERE id = :atividadeId LIMIT 1")
    fun getAtividade(atividadeId: Int): Atividade

    //Buscar todas as atividade com determinado nome
    @Query("SELECT * FROM atividade WHERE nome like :atividadeNome")
    fun buscarPeloNome(atividadeNome: String): List<Atividade>
}