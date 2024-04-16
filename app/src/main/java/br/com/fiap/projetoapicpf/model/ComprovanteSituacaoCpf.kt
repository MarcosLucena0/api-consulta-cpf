package br.com.fiap.projetoapicpf.model

data class ComprovanteSituacaoCpf (
        val cpf: String = "",
        val nome: String = "",
        val data_nascimento: String = "",
        val situacao_cadastral: String = "",
        val data_inscricao: String = "",
        val consulta_digito_verificador: String = ""
)

