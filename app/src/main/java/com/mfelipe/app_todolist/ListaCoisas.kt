package com.mfelipe.app_todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_coisas.*

class ListaCoisas : AppCompatActivity() {

    companion object {
        private const val REQUEST_CADASTRO = 1
    }

    val listaAtividades: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_coisas)


        CarregaLista()

        btnaddAtividade.setOnClickListener(){
            val CriarAtividade = Intent(this, salvaAtividade::class.java)
            startActivityForResult(CriarAtividade, REQUEST_CADASTRO)
        }

    }override fun onResume() {
        super.onResume()
        CarregaLista()
    }


    private fun CarregaLista() {
        val adapter = atividadeAdapter(listaAtividades)
        val layoutManager = LinearLayoutManager(this)

        rvListaCoisas.adapter = adapter
        rvListaCoisas.layoutManager = layoutManager
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CADASTRO && resultCode == Activity.RESULT_OK) {


            val novaAtividade: String? = data?.getStringExtra(salvaAtividade.ATIVIDADE)
            if(novaAtividade != null){
                listaAtividades.add(novaAtividade)
            }
        }

    }

}
