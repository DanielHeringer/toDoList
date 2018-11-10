package com.mfelipe.app_todolist.cenario.cenario_lista_atividade

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.mfelipe.app_todolist.entidade.Atividade
import com.mfelipe.app_todolist.R
import com.mfelipe.app_todolist.cenario.cenario_salva_atividade.salvaAtividade
import kotlinx.android.synthetic.main.activity_lista_coisas.*

class ListaCoisasActivity : AppCompatActivity(), ListaCoisasContract.View {

    companion object {
        private const val REQUEST_CADASTRO = 1
        private const val LISTA_ATIVIDADE = "Lista_atividades" // para salvar e restaurar a lista quando necessario
    }

    var listaAtividades: MutableList<Atividade> = mutableListOf()
    val presenter: ListaCoisasContract.Presenter = ListaCoisasPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_coisas)


        //exibeLista()

        btnaddAtividade.setOnClickListener(){
            val CriarAtividade = Intent(this, salvaAtividade::class.java)
            startActivity(CriarAtividade)
        }

    }override fun onResume() {
        super.onResume()
        presenter.onAtualizaLista(this)
    }


    override fun exibeLista(lista: MutableList<Atividade>) {

        listaAtividades = lista

        val adapter = atividadeAdapter(listaAtividades)

        //configura o clique em cada item da lista
        adapter.setOnItemClickListener{ indexAtividadeClicada ->
            val editaAtividade = Intent(this, salvaAtividade::class.java)
            editaAtividade.putExtra(salvaAtividade.ATIVIDADE, listaAtividades.get(indexAtividadeClicada))
            startActivity(editaAtividade)

        }

        adapter.configuraCLiqueLongo {indexAtividadeClicada ->
            presenter.onDeleteAtividade(this, listaAtividades.get(indexAtividadeClicada))
            true

        }

        val layoutManager = LinearLayoutManager(this)

        rvListaCoisas.adapter = adapter
        rvListaCoisas.layoutManager = layoutManager
    }
//}


//}

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
