package br.com.bicmsystems.notepadandroid.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.bicmsystems.notepadandroid.R
import br.com.bicmsystems.notepadandroid.model.Nota
import kotlinx.android.synthetic.main.item_nota.view.*

class MainListAdapter(
        private val context: Context,
        private val notas: List<Nota>,
        val listener: (Nota) -> Unit
) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val nota = notas[position]
        holder?.let {
            it.bindView(nota, listener)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(nota: Nota,
                     listener: (Nota) -> Unit) = with(itemView) {

            val tvTitulo = tvTitulo
            val tvDescricao = tvDescricao

            tvTitulo.text = nota.titulo
            tvDescricao.text = nota.descricao

            setOnClickListener { listener(nota) }

        }

    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
    }

}