package com.mfelipe.app_todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_coisas.*

class ListaCoisas : AppCompatActivity() {

    companion object {
        private const val REQUEST_CADASTRO = 1
        private const val LISTA_ATIVIDADE = "Lista_atividades" // para salvar e restaurar a lista quando necessario
    }

    var listaAtividades: MutableList<Atividade> = mutableListOf()

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

        //configura o clique em cada item da lista
        adapter.setOnItemClickListener{ indexAtividadeClicada -> indexAtividadeClicada
            val editaAtividade = Intent(this, salvaAtividade::class.java)
            editaAtividade.putExtra(salvaAtividade.ATIVIDADE, listaAtividades.get(indexAtividadeClicada))
            this.startActivityForResult(editaAtividade, REQUEST_CADASTRO)

        }

        adapter.configuraCLiqueLongo { indexAtividadeClicada-> listaAtividades.removeAt(indexAtividadeClicada)
            CarregaLista()
            true


        }

        val layoutManager = LinearLayoutManager(this)

        rvListaCoisas.adapter = adapter
        rvListaCoisas.layoutManager = layoutManager
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CADASTRO && resultCode == Activity.RESULT_OK) {


            val novaAtividade: Atividade? = data?.getSerializableExtra(salvaAtividade.ATIVIDADE) as Atividade
            if(novaAtividade != null){
                listaAtividades.add(novaAtividade)
            }
        }

    }

    //salva a lista caso o android venha a destruir

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putSerializable(LISTA_ATIVIDADE, listaAtividades as ArrayList<String>)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState != null){
           listaAtividades = savedInstanceState.getSerializable(LISTA_ATIVIDADE) as MutableList<Atividade>
        }
    }

}
