package br.com.bicmsystems.notepadandroid.view.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.bicmsystems.notepadandroid.model.Nota
import br.com.bicmsystems.notepadandroid.repository.NotaRepository

class MainViewModel : ViewModel() {

    val notaRepository = NotaRepository()
    val listaNotas: MutableLiveData<List<Nota>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun listaTodos() {

        isLoading.value = true

        notaRepository.listaTodos(
                onComplete = {
                    isLoading.value = false
                    listaNotas?.value = it!!
                },
                onError = {
                    isLoading.value = false
                    listaNotas?.value = arrayListOf()
                }
        )

    }

}