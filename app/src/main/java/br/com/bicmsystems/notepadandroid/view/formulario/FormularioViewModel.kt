package br.com.bicmsystems.notepadandroid.view.formulario

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.bicmsystems.notepadandroid.model.Nota
import br.com.bicmsystems.notepadandroid.model.ResponseStatus
import br.com.bicmsystems.notepadandroid.repository.NotaRepository

class FormularioViewModel : ViewModel() {

    val notaRepository = NotaRepository()

    val nota: MutableLiveData<Nota> = MutableLiveData()
    val responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun salva(id: String?, titulo: String?, descricao: String?) {

        isLoading.value = true

        val notaSalva = Nota(id, titulo, descricao)
        notaRepository.salva(
                notaSalva,
                onComplete = {
                    isLoading.value = false
                    it?.let {
                        nota.value = it
                        responseStatus.value = ResponseStatus(true, "Nota salva com sucesso")
                    }
                },
                onError = {
                    isLoading.value = false
                    responseStatus.value = ResponseStatus(false, "Erro ao salvar nota")
                }
        )

    }

}