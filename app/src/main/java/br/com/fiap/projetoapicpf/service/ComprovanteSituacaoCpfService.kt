package br.com.fiap.projetoapicpf.service

import br.com.fiap.projetoapicpf.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ComprovanteSituacaoCpfService {

    @GET("?token=k_j1x5ey5hZXNVSDqpj3RpJaPC2zwdlv3WZCWFSR&timeout=600")
   fun getComprovanteByCpf(@Query("cpf") cpf: String,
                           @Query("birthdate") dataNascimento: String
   ): Call<ResponseData>
}
