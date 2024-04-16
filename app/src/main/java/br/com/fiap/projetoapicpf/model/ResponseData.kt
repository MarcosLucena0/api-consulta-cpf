package br.com.fiap.projetoapicpf.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("code_message")
    val codeMessage: String,
    val header: Header,
    @SerializedName("data_count")
    val dataCount: Int,
    val data: List<Data>,
    val errors: List<Any>,
    @SerializedName("site_receipts")
    val siteReceipts: List<String>
)

data class Header(
    @SerializedName("api_version")
    val apiVersion: String,
    @SerializedName("api_version_full")
    val apiVersionFull: String,
    val product: String,
    val service: String,
    val parameters: Parameters,
    @SerializedName("client_name")
    val clientName: String,
    @SerializedName("token_name")
    val tokenName: String,
    val billable: Boolean,
    val price: String,
    @SerializedName("requested_at")
    val requestedAt: String,
    @SerializedName("elapsed_time_in_milliseconds")
    val elapsedTimeInMilliseconds: Int,
    @SerializedName("remote_ip")
    val remoteIp: String,
    val signature: String
)

data class Parameters(
    val birthdate: String,
    val cpf: String
)

data class Data(
    @SerializedName("ano_obito")
    val anoObito: Any?,
    @SerializedName("consulta_comprovante")
    val consultaComprovante: String,
    @SerializedName("consulta_datahora")
    val consultaDataHora: String,
    @SerializedName("consulta_digito_verificador")
    val consultaDigitoVerificador: String,
    val cpf: String,
    @SerializedName("data_inscricao")
    val dataInscricao: String,
    @SerializedName("data_nascimento")
    val dataNascimento: String,
    val nome: String,
    @SerializedName("nome_civil")
    val nomeCivil: String,
    @SerializedName("nome_social")
    val nomeSocial: String,
    @SerializedName("normalizado_ano_obito")
    val normalizadoAnoObito: Int,
    @SerializedName("normalizado_consulta_datahora")
    val normalizadoConsultaDataHora: String,
    @SerializedName("normalizado_cpf")
    val normalizadoCpf: String,
    @SerializedName("normalizado_data_inscricao")
    val normalizadoDataInscricao: String,
    @SerializedName("normalizado_data_nascimento")
    val normalizadoDataNascimento: String,
    val origem: String,
    @SerializedName("qrcode_url")
    val qrCodeUrl: String,
    @SerializedName("situacao_cadastral")
    val situacaoCadastral: String,
    @SerializedName("site_receipt")
    val siteReceipt: String
)

