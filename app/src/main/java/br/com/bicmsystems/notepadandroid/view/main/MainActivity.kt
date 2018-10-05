package br.com.bicmsystems.notepadandroid.view.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import br.com.bicmsystems.notepadandroid.R
import br.com.bicmsystems.notepadandroid.model.Nota
import br.com.bicmsystems.notepadandroid.view.exibicao.ExibeNotaActivity
import br.com.bicmsystems.notepadandroid.view.formulario.FormularioActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.loading.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    var listaNotas: List<Nota>? = null
    var isLoading = false
    public val FORMULARIO_REQUEST_CODE = 956

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel?.listaNotas?.observe(this, listaNotasObserver)
        mainViewModel?.isLoading?.observe(this, loadingObserver)
        mainViewModel.listaTodos()

        fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, FormularioActivity::class.java)
            startActivityForResult(intent, FORMULARIO_REQUEST_CODE)
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show()
        }
    }

    override fun onRestart() {
        super.onRestart()
        mainViewModel.listaTodos()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            FORMULARIO_REQUEST_CODE ->
                when (resultCode) {
                    Activity.RESULT_OK -> mainViewModel.listaTodos()
                    Activity.RESULT_CANCELED -> { }
                }
        }
    }

    private var listaNotasObserver = Observer<List<Nota>> {

        rvNotas.adapter = MainListAdapter(this@MainActivity, it!!, {
            val intent = Intent(this@MainActivity, ExibeNotaActivity::class.java)
            intent.putExtra("nota", it!!)
            startActivity(intent)
        })

        Toast.makeText(this@MainActivity, "Notas encontradas: ${it!!.count()}", Toast.LENGTH_SHORT).show()
        //val layoutManager = GridLayoutManager(this, 2)
        val layoutManager = LinearLayoutManager(this)
//        val layoutManager = StaggeredGridLayoutManager(
//                2, StaggeredGridLayoutManager.VERTICAL)
        rvNotas.layoutManager = layoutManager

    }

    private var loadingObserver = Observer<Boolean> {
        if(it == true) {
            containerLoading.visibility = View.VISIBLE
        } else {
            containerLoading.visibility = View.GONE
        }
    }

}
