package br.com.bicmsystems.notepadandroid.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import br.com.bicmsystems.notepadandroid.R
import br.com.bicmsystems.notepadandroid.model.Nota

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.loading.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    var listaNotas: List<Nota>? = null
    var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel?.listaNotas?.observe(this, listaNotasObserver)
        mainViewModel?.isLoading?.observe(this, loadingObserver)

        mainViewModel.listaTodos()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private var listaNotasObserver = Observer<List<Nota>> {

        rvNotas.adapter = MainListAdapter(this@MainActivity, it!!, {
            Toast.makeText(this@MainActivity, "Nota clicada: ${it!!.titulo}", Toast.LENGTH_SHORT).show()
        })

        Toast.makeText(this@MainActivity, "Notas encontradas: ${it!!.count()}", Toast.LENGTH_SHORT).show()

        //val layoutManager = GridLayoutManager(this, 2)
        // val layoutManager = LinearLayoutManager(this)
        val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)

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
