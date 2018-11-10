package com.mfelipe.app_todolist

import android.app.Activity
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_salva_atividade.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import javax.xml.validation.Validator
import android.R.attr.data



class salvaAtividade : AppCompatActivity() {


    companion object {
        public const val ATIVIDADE: String = "atividade"
    }

    val formataData = SimpleDateFormat("dd/MM/yyyy")
    val data = Date()
    val dataFormatada: String = formataData.format(data)

    var atividade: Atividade? = null
    var edicao : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salva_atividade)

        atividade = intent.getSerializableExtra(ATIVIDADE) as Atividade?
        if(atividade !=null) {
            carregaDados()
            edicao = true
        }

        editData.text = "Data: " + dataFormatada

        btnSalvar.setOnClickListener(){
            salvaAtividade()
        }
    }

    private fun carregaDados() {
        val editaAtividade: Atividade? = intent.getSerializableExtra(ATIVIDADE) as Atividade
        editCoisa.setText(editaAtividade!!.nome)
        editData.setText(editaAtividade!!.data)
    }



    private fun salvaAtividade(){
        //var atividade = Atividade(editCoisa.text.toString(), editData.text.toString())


            if (atividade == null) {
                atividade = Atividade(editCoisa.text.toString(),
                        dataFormatada)
            } else {
                atividade?.nome = editCoisa.text.toString()
                atividade?.data = dataFormatada
                // editData.text.toString()
            }

        val atividDao: AtividadeDao = AppDatabase.getIstance(this).atividadeDao()
        doAsync {
            atividDao.insert(atividade!!)

            uiThread {
                finish()
            }
        }

    }



}