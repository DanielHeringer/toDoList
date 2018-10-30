package com.mfelipe.app_todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_salva_atividade.*

class salvaAtividade : AppCompatActivity() {


    companion object {
        public const val ATIVIDADE: String = "nomeAtividade"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salva_atividade)


        btnSalvar.setOnClickListener(){
            salvaAtividade()
        }
    }


    private fun salvaAtividade(){
        val atividade = Atividade(editCoisa.text.toString())

        abreLista(atividade)

    }

    private fun abreLista(atividade: Atividade) {

        val abreLista = Intent(this, ListaCoisas::class.java)
        abreLista.putExtra(ATIVIDADE, atividade.nome)
        setResult(Activity.RESULT_OK, abreLista)
        finish()
    }


}