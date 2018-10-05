package br.com.bicmsystems.notepadandroid.view.exibicao

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.bicmsystems.notepadandroid.R
import br.com.bicmsystems.notepadandroid.model.Nota
import br.com.bicmsystems.notepadandroid.model.ResponseStatus
import br.com.bicmsystems.notepadandroid.view.formulario.FormularioActivity
import br.com.bicmsystems.notepadandroid.view.formulario.FormularioViewModel
import kotlinx.android.synthetic.main.activity_exibe_nota.*

class ExibeNotaActivity : AppCompatActivity() {

    private var nota: Nota? = null
    val FORMULARIO_REQUEST_CODE = 956
    lateinit var formularioViewModel: FormularioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibe_nota)
        formularioViewModel = ViewModelProviders.of(this).get(FormularioViewModel::class.java)
        val intent = intent
        nota = intent.getParcelableExtra("nota") as Nota
        mostraDados()
        btnEditar.setOnClickListener {
            val intent = Intent(this@ExibeNotaActivity, FormularioActivity::class.java)
            intent.putExtra("nota", nota)
            startActivityForResult(intent, FORMULARIO_REQUEST_CODE)
        }
    }

    private fun mostraDados() {
        tvTitulo.text = nota?.titulo?.toString()
        tvDescricao.text = nota?.descricao?.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            FORMULARIO_REQUEST_CODE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        val responseStatus = data?.getParcelableExtra("responseStatus") as ResponseStatus
                        if(responseStatus?.sucesso) {
                            Toast.makeText(this@ExibeNotaActivity, responseStatus?.mensagem, Toast.LENGTH_SHORT).show()
                            setResult(Activity.RESULT_OK)
                            nota = data?.getParcelableExtra("nota") as Nota
                            mostraDados()
                        }
                    }
                }
            }
        }
    }





}
