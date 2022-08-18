package br.com.creditas.projethom.dto

data class CreditAnalysisRequest(
    val cpf: String,
    val name: String,
    val lastName: String,
    val birthDate: String
)
