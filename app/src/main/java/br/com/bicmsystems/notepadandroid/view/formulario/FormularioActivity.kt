package br.com.bicmsystems.notepadandroid.view.formulario

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.bicmsystems.notepadandroid.R
import br.com.bicmsystems.notepadandroid.model.Nota
import br.com.bicmsystems.notepadandroid.model.ResponseStatus
import kotlinx.android.synthetic.main.activity_formulario.*
import java.util.*

class FormularioActivity : AppCompatActivity() {

    lateinit var formularioViewModel: FormularioViewModel
    var nota: Nota? = null
    var responseStatus: ResponseStatus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        val intent = intent
        if(intent != null && intent.hasExtra("nota")) {
            nota = intent.getParcelableExtra("nota") as Nota
            edtTitulo.editText?.setText(nota?.titulo)
            edtDescricao.editText?.setText(nota?.descricao)
        }
        formularioViewModel = ViewModelProviders.of(this).get(FormularioViewModel::class.java)
        formularioViewModel.responseStatus?.observe(this, Observer {
            if(it?.sucesso == true) {
                val intent = Intent()
                intent.putExtra("nota", formularioViewModel.nota!!.value)
                intent.putExtra("responseStatus", formularioViewModel.responseStatus.value)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@FormularioActivity, it?.mensagem, Toast.LENGTH_LONG).show()
            }
        })
        btnSalva.setOnClickListener {
            salva()
        }
    }

    fun salva() {
        if(nota == null) {
            nota = Nota(null)
            nota?.data = Calendar.getInstance().time.toString()
        }
        val id = nota?.id
        val titulo = edtTitulo.editText?.text.toString()
        val descricao = edtDescricao.editText?.text.toString()
        formularioViewModel.salva(id, titulo, descricao)
    }


}
