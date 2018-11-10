package com.mfelipe.app_todolist.cenario.cenario_salva_atividade

import java.text.SimpleDateFormat;
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast
import com.mfelipe.app_todolist.R
import kotlinx.android.synthetic.main.activity_salva_atividade.*
import java.util.*
import com.mfelipe.app_todolist.entidade.Atividade


class salvaAtividade : AppCompatActivity(), salvaAtividadeContract.View {


    companion object {
        public const val ATIVIDADE: String = "atividade"
    }

    val formataData = SimpleDateFormat("dd/MM/yyyy")
    val data = Date()
    val dataFormatada: String = formataData.format(data)

    var atividade: Atividade? = null
    var edicao : Boolean = false

    val presenter: salvaAtividadeContract.Presenter = salvaAtividadePresenter(this)


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

        atividade?.let {atividade ->
            presenter.onSalvaAtividade(this, atividade)
        }

    }

   override fun salvoComSucesso(){
        Toast.makeText(this, "salvo com sucesso.", Toast.LENGTH_SHORT).show()
        finish()
    }



}