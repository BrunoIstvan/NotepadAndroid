package br.com.bicmsystems.notepadandroid.api

import br.com.bicmsystems.notepadandroid.model.Nota
import retrofit2.Call
import retrofit2.http.*

interface NotaAPI {

    @GET("/nota")
    fun listaTodos() : Call<List<Nota>>

    @GET("/nota/{titulo}/containing")
    fun obtemPorTitulo(@Path("titulo") titulo: String) : Call<List<Nota>>

    @POST("/nota")
    fun salva(@Body nota: Nota) : Call<Nota>

    @DELETE("/{id}")
    fun apagaPorId(@Path("id") id: String)

}