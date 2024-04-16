package br.com.fiap.projetoapicpf.service

import br.com.fiap.projetoapicpf.model.ResponseData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val URL = "https://api.infosimples.com/api/v2/consultas/receita-federal/cpf/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getComprovanteSituacaoCpfService(): ComprovanteSituacaoCpfService {
        return retrofitFactory.create(ComprovanteSituacaoCpfService::class.java)
    }
}