package br.com.bicmsystems.notepadandroid.repository

import br.com.bicmsystems.notepadandroid.api.getNotaAPI
import br.com.bicmsystems.notepadandroid.model.Nota
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotaRepository {

    fun listaTodos(
            onComplete: (List<Nota>?) -> Unit,
            onError: (Throwable?) -> Unit) {

        getNotaAPI()
            .listaTodos()
                .enqueue(object : Callback<List<Nota>>{
                    override fun onFailure(call: Call<List<Nota>>?, t: Throwable?) {
                        onError(t)
                    }
                    override fun onResponse(call: Call<List<Nota>>?, response: Response<List<Nota>>?) {
                       onComplete(response?.body())
                    }
                })
    }

    fun salva(nota: Nota,
              onComplete: (Nota?) -> Unit,
              onError: (Throwable?) -> Unit) {

        getNotaAPI().salva(nota)
                .enqueue(object : Callback<Nota> {
                    override fun onFailure(call: Call<Nota>?, t: Throwable?) {
                        onError(t)
                    }
                    override fun onResponse(call: Call<Nota>?, response: Response<Nota>?) {
                        onComplete(response?.body())
                    }
                })
    }




}