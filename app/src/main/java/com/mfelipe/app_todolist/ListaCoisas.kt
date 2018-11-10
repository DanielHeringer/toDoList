package com.mfelipe.app_todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_coisas.*
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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
            startActivity(CriarAtividade)
        }

    }override fun onResume() {
        super.onResume()
        CarregaLista()
    }


    private fun CarregaLista() {
    // FAzer de forma assincrona
       val atividadeDao = AppDatabase.getIstance(this).atividadeDao()
       doAsync {
           listaAtividades = atividadeDao.getAll() as MutableList<Atividade>

           activityUiThreadWithContext {
               val adapter = atividadeAdapter(listaAtividades)

               //configura o clique em cada item da lista
               adapter.setOnItemClickListener{ indexAtividadeClicada ->
                   val editaAtividade = Intent(this, salvaAtividade::class.java)
                   editaAtividade.putExtra(salvaAtividade.ATIVIDADE, listaAtividades.get(indexAtividadeClicada))
                   startActivity(editaAtividade)

               }

               adapter.configuraCLiqueLongo {indexAtividadeClicada ->
                doAsync {
                    atividadeDao.delete(listaAtividades.get(indexAtividadeClicada))

                   uiThread {
                       CarregaLista()
                   }
                }
                   true

               }

               val layoutManager = LinearLayoutManager(this)

               rvListaCoisas.adapter = adapter
               rvListaCoisas.layoutManager = layoutManager
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
